package br.com.fiap.healthtrack.medidas.atividadefisica;

import br.com.fiap.healthtrack.medidas.QualificadorMedida;

/**
 * @author Sid Os tipos de atividade física suportados pelo health Track
 *
 */
public enum TipoAtividadeFisica implements QualificadorMedida {
	CORRIDA("Corrida"), CAMINHADA("Caminhada"), PEDALADA("Pedalada"), MUSCULACAO("Musculação");

	private String descricao;
	private String id;

	/**
	 * @param descricao
	 */
	private TipoAtividadeFisica(String descricao) {
		this.descricao = descricao;
	}
	
	

	private TipoAtividadeFisica(String descricao, String id) {
		this.descricao = descricao;
		this.id = id;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.fiap.healthtrack.medidas.QualificadorMedida#getDescricao()
	 */
	@Override
	public String getDescricao() {

		return descricao;
	}
	

	public String get_id() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
