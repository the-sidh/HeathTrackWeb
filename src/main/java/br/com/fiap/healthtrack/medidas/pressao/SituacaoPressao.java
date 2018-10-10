package br.com.fiap.healthtrack.medidas.pressao;

import br.com.fiap.healthtrack.medidas.QualificadorMedida;

/**
 * Define a situacao de pressao conforme especificado para o health track
 * @author Sid
 *
 */
public enum SituacaoPressao implements QualificadorMedida {
	NORMAL("Normal"), ELEVADA("Elevada"), ABAIXO_DO_NORMAL("Abaixo do normal");
	private String descricao;

	/**
	 *
	 * @param descricao
	 */
	private SituacaoPressao(String descricao) {
		this.descricao = descricao;
	}

	/* (non-Javadoc)
	 * @see br.com.fiap.healthtrack.medidas.QualificadorMedida#getDescricao()
	 */
	@Override
	public String getDescricao() {

		return descricao;
	}

}
