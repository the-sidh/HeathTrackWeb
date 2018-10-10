package br.com.fiap.healthtrack.medidas.pressao;

import br.com.fiap.healthtrack.medidas.Medida;

/**
 * pressão arterial no health track
 * @author Sid
 *
 */
public class Pressao extends Medida{

	private int sistolica;
	private int diastolica;
	
		
	/**
	 * @param sistolica
	 * @param diastolica
	 */
	public Pressao(int sistolica, int diastolica) {
		super();
		this.sistolica = sistolica;
		this.diastolica = diastolica;
	}
	public Pressao() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * retorna a pressao sistolica
	 * @return
	 */
	public int getSistolica() {
		return sistolica;
	}
	/**
	 * define nova pressao sistolica
	 * @param cistolica
	 */
	public void setSistolica(int cistolica) {
		this.sistolica = cistolica;
	}
	/**
	 * retorna a pressao diastolica
	 * @return
	 */
	public int getDiastolica() {
		return diastolica;
	}
	
	/**
	 * define nova pressao diastolica
	 * @param diastolica
	 */
	public void setDiastolica(int diastolica) {
		this.diastolica = diastolica;
	}
	
	
	
}
