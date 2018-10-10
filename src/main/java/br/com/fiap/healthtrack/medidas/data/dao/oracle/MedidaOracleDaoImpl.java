package br.com.fiap.healthtrack.medidas.data.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.healthtrack.database.ConnectionManagerJDBC;
import br.com.fiap.healthtrack.database.ConnectionManagerOracleImpl;
import br.com.fiap.healthtrack.database.TableNotIdentifiedException;
import br.com.fiap.healthtrack.medidas.Medida;
import br.com.fiap.healthtrack.medidas.QualificadorMedida;
import br.com.fiap.healthtrack.medidas.alimentacao.Alimentacao;
import br.com.fiap.healthtrack.medidas.alimentacao.TipoAlimentacao;
import br.com.fiap.healthtrack.medidas.atividadefisica.AtividadeFisica;
import br.com.fiap.healthtrack.medidas.atividadefisica.TipoAtividadeFisica;
import br.com.fiap.healthtrack.medidas.data.dao.MedidaDao;
import br.com.fiap.healthtrack.medidas.peso.Peso;
import br.com.fiap.healthtrack.medidas.pressao.Pressao;

public class MedidaOracleDaoImpl<K extends Medida> implements MedidaDao<K> {
	private static final String ALIMENTACAO = "br.com.fiap.healthtrack.medidas.alimentacao.Alimentacao";
	private static final String ATIVIDADE_FISICA = "br.com.fiap.healthtrack.medidas.atividadefisica.AtividadeFisica";
	private static final String PESO = "br.com.fiap.healthtrack.medidas.peso.Peso";
	private static final String PRESSAO = "br.com.fiap.healthtrack.medidas.pressao.Pressao";

	private Class<K> entityBeanType;

	ConnectionManagerJDBC connectionManager = ConnectionManagerOracleImpl.getInstance();

	public MedidaOracleDaoImpl(Class<K> entityBeanType) {

		this.entityBeanType = entityBeanType;
	}

	@Override
	public List<K> getListaMedidas() {
		List<K> list = new ArrayList<K>();
		PreparedStatement stmt = null;
		try {

			String query;
			ResultSet rs = null;
			query = "SELECT * FROM " + getTableName();

			stmt = connectionManager.getConnection().prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {

				K k = fetchMedida(rs);
				list.add(k);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TableNotIdentifiedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			closeResources(stmt);
		}
		return list;
	}

	@Override
	public List<K> getListaMedidas(int first, int quant) {
		return getListaMedidas().stream().skip(first).limit(quant).collect(Collectors.toList());
	}

	@Override
	public K getMedida(int id) {
		K medida = null;
		PreparedStatement stmt = null;
		try {
			ResultSet rs = null;
			String[] param = { getTableName(), getIdFieldName(), new Integer(id).toString() };
			String query = MessageFormat.format("SELECT * FROM {0} where {1} = {2}", param);

			stmt = connectionManager.getConnection().prepareStatement(query);
			rs = stmt.executeQuery();

			if (rs.next()) {

				medida = fetchMedida(rs);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TableNotIdentifiedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			closeResources(stmt);
		}
		return medida;
	}

	@Override
	public void insertMedidas(K medida) {
		PreparedStatement stmt = null;
		Connection connection = null;
		String query = null;
		try {

			String[] param = { getTableName(), getFieldsFromType(), getValuesFromType() };
			query = MessageFormat.format("INSERT INTO {0} ({1}) VALUES({2})", param);

			connection = connectionManager.getConnection();
			stmt = connection.prepareStatement(query);
			configureFields(medida, stmt);
			stmt.executeQuery();
			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (TableNotIdentifiedException e1) {
			e1.printStackTrace();
		} catch (Throwable e1) {
			e1.printStackTrace();
		} finally {
			closeResources(stmt);
		}

	}

	@Override
	public void updateMedida(K medida) {
		PreparedStatement stmt = null;
		Connection connection = null;
		try {
			String query;
			String[] param = { getTableName(), getUpdateParamFromType(), getIdFieldName(), Integer.toString(medida.getId()) };
			query = MessageFormat.format("UPDATE {0} SET {1} where {2}={3}", param);

			connection = connectionManager.getConnection();
			stmt = connection.prepareStatement(query);
			configureFields(medida, stmt);

			stmt.executeQuery();
			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (TableNotIdentifiedException e1) {
			e1.printStackTrace();
		} finally {
			closeResources(stmt);
		}
	}

	@Override
	public void deleteMedida(K medida) {
		deleteMedida(medida.getId());
	}

	@Override
	public void deleteMedida(int id) {
		PreparedStatement stmt = null;
		Connection connection = null;
		try {
			String query;
			String[] param = { getTableName(), getIdFieldName(), Integer.toString(id) };
			query = MessageFormat.format("DELETE FROM {0} WHERE {1}={2}", param);

			connection = connectionManager.getConnection();
			stmt = connection.prepareStatement(query);

			stmt.executeQuery();
			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (TableNotIdentifiedException e1) {
			e1.printStackTrace();
		} finally {
			closeResources(stmt);
		}
	}

	@Override
	public void purgeAll() {
		PreparedStatement stmt = null;
		Connection connection = null;
		try {
			String[] param = { getTableName() };
			String query = MessageFormat.format("DELETE FROM {0}", param);

			connection = connectionManager.getConnection();
			stmt = connection.prepareStatement(query);

			stmt.executeQuery();
			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (TableNotIdentifiedException e) {
			e.printStackTrace();
		} finally {
			closeResources(stmt);
		}
	}

	private void closeResources(PreparedStatement stmt) {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connectionManager.closeConnection();
	}

	private void configureFields(K medida, PreparedStatement stmt) {
		try {
			if (entityBeanType.getName().equals(PRESSAO)) {
				Pressao pressao = (Pressao) medida;
				stmt.setInt(1, pressao.getSistolica());
				stmt.setInt(2, pressao.getDiastolica());
				stmt.setDate(3, new java.sql.Date(pressao.getDate().getTime()));
				stmt.setInt(4, getUserId());
			} else if (entityBeanType.getName().equals(PESO)) {
				Peso peso = (Peso) medida;
				stmt.setDouble(1, peso.getPesoEmKg());
				stmt.setDate(2, new java.sql.Date(peso.getDate().getTime()));
				stmt.setInt(3, getUserId());
			} else if (entityBeanType.getName().equals(ATIVIDADE_FISICA)) {
				AtividadeFisica atFis = (AtividadeFisica) medida;
				stmt.setString(1, atFis.getDescricao() != null ? atFis.getDescricao() : "");
				stmt.setInt(2, atFis.getCalorias());
				stmt.setDate(3, new java.sql.Date(atFis.getDate().getTime()));
				stmt.setInt(4, getIdByQualificadorMedida(atFis.getTipo()));
				stmt.setInt(5, getUserId());
			} else if (entityBeanType.getName().equals(ALIMENTACAO)) {
				Alimentacao alim = (Alimentacao) medida;
				stmt.setString(1, alim.getDescricao() != null ? alim.getDescricao() : "");
				stmt.setInt(2, alim.getCalorias());
				stmt.setDate(3, new java.sql.Date(alim.getDate().getTime()));
				stmt.setInt(4, getIdByQualificadorMedida(alim.getTipo()));
				stmt.setInt(5, getUserId());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private int getUserId() {
		return 1;
	}

	private String getFieldsFromType() {
		String fields = null;
		if (entityBeanType.getName().equals(PRESSAO))
			fields = "id_pressao, n_pressao_sist, n_pressao_dias, dt_pressao, t_usuario_id_usuario";
		if (entityBeanType.getName().equals(PESO))
			fields = "id_pesagem, n_peso, dt_pesagem, t_usuario_id_usuario";
		if (entityBeanType.getName().equals(ATIVIDADE_FISICA))
			fields = "id_atividade, ds_atividade, n_calorias_gastas, dt_atividade, t_tipo_ativ_id_tipo_ativ, t_usuario_id_usuario";
		if (entityBeanType.getName().equals(ALIMENTACAO))
			fields = "id_alimentacao, ds_alimentao, n_calorias, dt_alimentacao, t_tipo_alim_id_tipo_alim, t_usuario_id_usuario";

		return fields;
	}

	private String getUpdateParamFromType() {
		String fields = null;
		if (entityBeanType.getName().equals(PRESSAO))
			fields = "n_pressao_sist =? , n_pressao_dias=?, dt_pressao=?, t_usuario_id_usuario=?";
		if (entityBeanType.getName().equals(PESO))
			fields = " n_peso=?, dt_pesagem=?, t_usuario_id_usuario=?";
		if (entityBeanType.getName().equals(ATIVIDADE_FISICA))
			fields = "ds_atividade=?, n_calorias_gastas=?, dt_atividade=?, t_tipo_ativ_id_tipo_ativ=?, t_usuario_id_usuario=?";
		if (entityBeanType.getName().equals(ALIMENTACAO))
			fields = "ds_alimentao=?, n_calorias, dt_alimentacao=?, t_tipo_alim_id_tipo_alim=?, t_usuario_id_usuario=?";

		return fields;
	}

	private String getValuesFromType() {
		String values = null;
		if (entityBeanType.getName().equals(PRESSAO))
			values = "SQ_PRESSAO.NEXTVAL,?,?,?,?";
		if (entityBeanType.getName().equals(PESO))
			values = "SQ_PESO.NEXTVAL,?,?,?";
		if (entityBeanType.getName().equals(ATIVIDADE_FISICA))
			values = "SQ_ATIVIDADE_FISICA.NEXTVAL,?,?,?,?,?";
		if (entityBeanType.getName().equals(ALIMENTACAO))
			values = "SQ_ALIMENTACAO.NEXTVAL,?,?,?,?,?";
		return values;

	}

	private String getTableName() throws TableNotIdentifiedException {
		String tableName = null;
		if (entityBeanType.getName().equals(PRESSAO))
			tableName = "T_PRESSAO";
		if (entityBeanType.getName().equals(PESO))
			tableName = "T_PESOS";
		if (entityBeanType.getName().equals(ATIVIDADE_FISICA))
			tableName = "T_ATIVIDADE";
		if (entityBeanType.getName().equals(ALIMENTACAO))
			tableName = "T_ALIMENTACAO";
		if (tableName == null) {
			throw new TableNotIdentifiedException();
		}
		return tableName;

	}

	private String getIdFieldName() throws TableNotIdentifiedException {
		String tableName = null;
		if (entityBeanType.getName().equals(PRESSAO))
			tableName = "ID_PRESSAO";
		if (entityBeanType.getName().equals(PESO))
			tableName = "ID_PESAGEM";
		if (entityBeanType.getName().equals(ATIVIDADE_FISICA))
			tableName = "ID_ATIVIDADE";
		if (entityBeanType.getName().equals(ALIMENTACAO))
			tableName = "ID_ALIMENTACAO";
		if (tableName == null) {
			throw new TableNotIdentifiedException();
		}
		return tableName;

	}

	private K fetchMedida(ResultSet rs) throws SQLException {
		if (entityBeanType.getName().equals(PRESSAO)) {
			int id = rs.getInt("id_pressao");
			java.sql.Date data = rs.getDate("dt_pressao");
			int sist = rs.getInt("n_pressao_sist");
			int diast = rs.getInt("n_pressao_dias");
			Pressao pressao = new Pressao(sist, diast);
			pressao.setDate(data);
			pressao.setId(id);
			return (K) pressao;
		}
		if (entityBeanType.getName().equals(PESO)) {
			int id = rs.getInt("id_pesagem");
			java.sql.Date data = rs.getDate("dt_pesagem");
			float pesoEmKg = rs.getFloat("n_peso");
			Peso peso = new Peso(pesoEmKg);
			peso.setDate(data);
			peso.setId(id);
			return (K) peso;
		} else if (entityBeanType.getName().equals(ATIVIDADE_FISICA)) {
			int id = rs.getInt("id_atividade");
			java.sql.Date data = rs.getDate("dt_atividade");
			int idTipoAtividadeFisica = rs.getInt("t_tipo_ativ_id_tipo_ativ");
			int calorias = rs.getInt("n_calorias_gastas");
			String descricao = rs.getString("ds_atividade");
			QualificadorMedida tipoAtividadeFisica = getQualificadorMedida(ATIVIDADE_FISICA, idTipoAtividadeFisica);
			AtividadeFisica atFis = new AtividadeFisica((TipoAtividadeFisica) tipoAtividadeFisica, calorias, descricao);
			atFis.setDate(data);
			atFis.setId(id);
			return (K) atFis;
		} else if (entityBeanType.getName().equals(ALIMENTACAO)) {
			int id = rs.getInt("id_alimentacao");
			java.sql.Date data = rs.getDate("dt_alimentacao");
			int idTipoAlimentacao = rs.getInt("t_tipo_alim_id_tipo_alim");
			int calorias = rs.getInt("n_calorias");
			String descricao = rs.getString("ds_alimentao");
			QualificadorMedida tipoAlimentacao = getQualificadorMedida(ALIMENTACAO, idTipoAlimentacao);
			Alimentacao alimentacao = new Alimentacao((TipoAlimentacao) tipoAlimentacao, calorias, descricao);
			alimentacao.setDate(data);
			alimentacao.setId(id);
			return (K) alimentacao;
		}
		return null;
	}

	private QualificadorMedida getQualificadorMedida(String tipo, int id) {
		QualificadorMedida qualificadorMedida = null;
		if (tipo.equals(ATIVIDADE_FISICA) && id == 1) {
			qualificadorMedida = TipoAtividadeFisica.CAMINHADA;
		} else if (tipo.equals(ATIVIDADE_FISICA) && id == 2) {
			qualificadorMedida = TipoAtividadeFisica.CORRIDA;
		} else if (tipo.equals(ATIVIDADE_FISICA) && id == 3) {
			qualificadorMedida = TipoAtividadeFisica.MUSCULACAO;
		} else if (tipo.equals(ATIVIDADE_FISICA) && id == 4) {
			qualificadorMedida = TipoAtividadeFisica.PEDALADA;
		} else if (tipo.equals(ALIMENTACAO) && id == 1) {
			qualificadorMedida = TipoAlimentacao.ALMOCO;
		} else if (tipo.equals(ALIMENTACAO) && id == 2) {
			qualificadorMedida = TipoAlimentacao.CAFE_DA_MANHA;
		} else if (tipo.equals(ALIMENTACAO) && id == 3) {
			qualificadorMedida = TipoAlimentacao.FRUTA;
		} else if (tipo.equals(ALIMENTACAO) && id == 4) {
			qualificadorMedida = TipoAlimentacao.JANTAR;
		} else if (tipo.equals(ALIMENTACAO) && id == 5) {
			qualificadorMedida = TipoAlimentacao.LANCHE_LEVE;
		}
		return qualificadorMedida;

	}

	private int getIdByQualificadorMedida(QualificadorMedida qualificadorMedida) {
		int id = 0;
		if (qualificadorMedida == TipoAtividadeFisica.CAMINHADA || qualificadorMedida == TipoAlimentacao.ALMOCO) {
			id = 1;
		}
		if (qualificadorMedida == TipoAtividadeFisica.CORRIDA || qualificadorMedida == TipoAlimentacao.CAFE_DA_MANHA) {
			id = 2;
		}
		if (qualificadorMedida == TipoAtividadeFisica.MUSCULACAO || qualificadorMedida == TipoAlimentacao.FRUTA) {
			id = 3;
		}
		if (qualificadorMedida == TipoAtividadeFisica.PEDALADA || qualificadorMedida == TipoAlimentacao.JANTAR) {
			id = 4;
		}
		if (qualificadorMedida == TipoAlimentacao.LANCHE_LEVE) {
			id = 5;
		}

		return id;

	}

	@Override
	public K getMedida(String _id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMedida(String _id) {
		// TODO Auto-generated method stub
		
	}

}
