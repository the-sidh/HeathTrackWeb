package br.com.fiap.healthtrack.medidas.peso;

import br.com.fiap.healthtrack.medidas.Medida;

/**
 * Define medida de peso para o health track
 * @author Sid
 *
 */
public class Peso extends Medida{
	
	private double pesoEmKg;
	
	/**
	 * @param pesoEmKg
	 */
	public Peso(float pesoEmKg) {
		super();
		this.pesoEmKg = pesoEmKg;
	}

	public Peso() {
		super();
	}

	
	
	/**
	 * retorna o peso
	 * @return
	 */
	public double getPesoEmKg() {
		return pesoEmKg;
	}

	/**
	 * define um novo peso
	 * @param pesoEmKg
	 */
	public void setPesoEmKg(double pesoEmKg) {
		this.pesoEmKg = pesoEmKg;
	}
	
	

}
