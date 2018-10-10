package br.com.fiap.healthtrack.medidas;

/**
 * @author Sid
 * Define qualificadores para tipos de medida, usados por subtipos do MedidaCalorico 
 */
public interface QualificadorMedida {

		/**
		 * Retorna uma descricao conveniente para exibicao
		 * @return descricao
		 */
		String getDescricao();
}
