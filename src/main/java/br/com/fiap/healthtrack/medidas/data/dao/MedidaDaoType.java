package br.com.fiap.healthtrack.medidas.data.dao;

import br.com.fiap.healthtrack.database.DBDomain;

public class MedidaDaoType {

	private MedidaType type;
	private DBDomain domais;
	
	
	public MedidaDaoType(MedidaType type, DBDomain domais) {
		super();
		this.type = type;
		this.domais = domais;
	}
	
	public MedidaType getMedidaType() {
		return type;
	}
	
	public DBDomain getDBDomain() {
		return domais;
	}
	
	
}


