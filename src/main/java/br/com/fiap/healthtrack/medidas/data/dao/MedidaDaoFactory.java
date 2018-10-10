package br.com.fiap.healthtrack.medidas.data.dao;

import br.com.fiap.healthtrack.database.DBDomain;
import br.com.fiap.healthtrack.medidas.alimentacao.Alimentacao;
import br.com.fiap.healthtrack.medidas.atividadefisica.AtividadeFisica;
import br.com.fiap.healthtrack.medidas.data.dao.mock.MedidaTestDaoImpl;
import br.com.fiap.healthtrack.medidas.data.dao.mongodb.AlimentacaoMongoDBDaoImpl;
import br.com.fiap.healthtrack.medidas.data.dao.mongodb.AtividadeFisicaMongoDBDaoImpl;
import br.com.fiap.healthtrack.medidas.data.dao.mongodb.PesoMongoDBDaoImpl;
import br.com.fiap.healthtrack.medidas.data.dao.mongodb.PressaoMongoDBDaoImpl;
import br.com.fiap.healthtrack.medidas.data.dao.oracle.MedidaOracleDaoImpl;
import br.com.fiap.healthtrack.medidas.peso.Peso;
import br.com.fiap.healthtrack.medidas.pressao.Pressao;

public class MedidaDaoFactory {

	private static MedidaDaoFactory instance;


	public static MedidaDaoFactory getInstance() {
		if (instance == null) {
			instance = new MedidaDaoFactory();
		}
		return instance;
	}

	public MedidaDao<?> getMedidaDao(MedidaDaoType type){
		if(type.getMedidaType().equals(MedidaType.PESO) && type.getDBDomain().equals(DBDomain.TESTE)) {
			return new MedidaTestDaoImpl<Peso>();	
		}
		else if(type.getMedidaType().equals(MedidaType.PRESSAO) && type.getDBDomain().equals(DBDomain.TESTE)) {
			return new MedidaTestDaoImpl<Pressao>();	
		}
		else if(type.getMedidaType().equals(MedidaType.ALIMENTACAO) && type.getDBDomain().equals(DBDomain.TESTE)) {
			return new MedidaTestDaoImpl<Alimentacao>();	
		}
		else if(type.getMedidaType().equals(MedidaType.ATIVIDADE_FISICA) && type.getDBDomain().equals(DBDomain.TESTE)) {
			return new MedidaTestDaoImpl<AtividadeFisica>();	
		}
		
		if(type.getMedidaType().equals(MedidaType.PESO) && type.getDBDomain().equals(DBDomain.JDBC)) {
			return new MedidaOracleDaoImpl<>(Peso.class);	
		}
		else if(type.getMedidaType().equals(MedidaType.PRESSAO) && type.getDBDomain().equals(DBDomain.JDBC)) {
			return new MedidaOracleDaoImpl<>(Pressao.class);	
		}
		else if(type.getMedidaType().equals(MedidaType.ALIMENTACAO) && type.getDBDomain().equals(DBDomain.JDBC)) {
			return new MedidaOracleDaoImpl<>(Alimentacao.class);	
		}
		else if(type.getMedidaType().equals(MedidaType.ATIVIDADE_FISICA) && type.getDBDomain().equals(DBDomain.JDBC)) {
			return new MedidaOracleDaoImpl<>(AtividadeFisica.class);	
		}


		if(type.getMedidaType().equals(MedidaType.PESO) && type.getDBDomain().equals(DBDomain.NOSQL)) {
			return new PesoMongoDBDaoImpl();	
		}
		else if(type.getMedidaType().equals(MedidaType.PRESSAO) && type.getDBDomain().equals(DBDomain.NOSQL)) {
			return new PressaoMongoDBDaoImpl();	
		}
		else if(type.getMedidaType().equals(MedidaType.ALIMENTACAO) && type.getDBDomain().equals(DBDomain.NOSQL)) {
			return new AlimentacaoMongoDBDaoImpl();	
		}
		else if(type.getMedidaType().equals(MedidaType.ATIVIDADE_FISICA) && type.getDBDomain().equals(DBDomain.NOSQL)) {
			return new AtividadeFisicaMongoDBDaoImpl();	
		}
		return null;
	
	}
}


