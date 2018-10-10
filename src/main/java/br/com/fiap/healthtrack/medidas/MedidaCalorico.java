package br.com.fiap.healthtrack.medidas;


/**
 * @author Sidh
 * Esta classe é usada para as Atividades Físicas e alimentação
 * as duas medidas do heathTrack que possuem um qualificador de tipo
 * uma descrição opcional e um valor de calorias associado
 */
public class MedidaCalorico<K extends QualificadorMedida> extends Medida {

	private K tipo;
	private int calorias;
	private String descricao;
	
	/**
	 * @param tipo
	 * @param calorias
	 * @param descricao
	 */
	public MedidaCalorico(K tipo, int calorias, String descricao) {
		super();
		this.tipo = tipo;
		this.calorias = calorias;
		this.descricao = descricao;
	}

	public MedidaCalorico() {
		super();
	}

	/**
	 * Retorna o qualificador de medida associado
	 * @return tipo
	 */
	public QualificadorMedida getTipo() {
		return tipo;
	}

	/**
	 * define um novo qualificador de medida
	 * @param tipo
	 */
	public void setTipo(K tipo) {
		this.tipo = tipo;
	}

	/**
	 * retorna o numero de calorias ingerido ou gasto
	 * @return
	 */
	public int getCalorias() {
		return calorias;
	}

	/**
	 * define um novo valor para as calorias
	 * @param calorias
	 */
	public void setCalorias(int calorias) {
		this.calorias = calorias;
	}

	/**
	 * retorna a descricao
	 * @return
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * define uma nova descricao
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	
	
	
	
}
