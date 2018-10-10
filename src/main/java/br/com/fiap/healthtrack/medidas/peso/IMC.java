package br.com.fiap.healthtrack.medidas.peso;

import br.com.fiap.healthtrack.medidas.QualificadorMedida;


/**
 * @author Sid
 * Representa o IMC conforme definido no health track
 *
 */
public enum IMC implements QualificadorMedida {
	MAGREZA_GRAVE("Magreza grave"), MAGREZA_MODERADA("Magreza moderada"), MAGREZA_LEVE("Magreza leve"), SAUDAVEL("Saudável"), SOBREPESO("Sobrepeso"), OBESIDADE_1("Obesidade grau 1"), OBESIDADE_2("Obesidade grau 2");

private String descricao;
	
	private IMC(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

}
