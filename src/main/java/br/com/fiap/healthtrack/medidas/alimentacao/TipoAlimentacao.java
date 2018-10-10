package br.com.fiap.healthtrack.medidas.alimentacao;

import br.com.fiap.healthtrack.medidas.QualificadorMedida;

/**
 * @author Sid
 * todos os tipos de alimenta��o suportados pelo healthTrack de acordo com especifica��o
 *
 */
public enum TipoAlimentacao implements QualificadorMedida {
	CAFE_DA_MANHA("Caf� da manh�"), ALMOCO("Almo�o"), JANTAR("Jantar"), LANCHE_LEVE("Lanche leve"), FRUTA("Fruta");
	
	private String descricao;
	private String id;
	
	/**
	 * @param descricao
	 */
	private TipoAlimentacao(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * @param descricao
	 */
	private TipoAlimentacao(String descricao, String id) {
		this.descricao = descricao;
		this.id = id;
	}

	/* (non-Javadoc)
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
