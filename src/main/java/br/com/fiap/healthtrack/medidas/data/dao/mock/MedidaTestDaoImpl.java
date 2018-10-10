package br.com.fiap.healthtrack.medidas.data.dao.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.healthtrack.medidas.Medida;
import br.com.fiap.healthtrack.medidas.data.dao.MedidaDao;

public class MedidaTestDaoImpl<K extends Medida> implements MedidaDao<K> {

	List<K> medidas = new ArrayList<K>();	
	
	@Override
	public List<K> getListaMedidas() {
		return medidas;
	}

	@Override
	public List<K> getListaMedidas(int first, int quant) {
		return medidas.stream().skip(first).limit(quant).collect(Collectors.toList());
	}

	@Override
	public void insertMedidas(K medida) {
		medidas.add(medida);
	}

	@Override
	public void updateMedida(K medida) {
		medidas.set(medidas.indexOf(medida), medida);
	}

	@Override
	public void deleteMedida(K medida) {
		medidas.remove(medida);
	}

	@Override
	public K getMedida(int id) {
		return medidas.stream().filter(medida -> medida.getId()==id).limit(1).collect(Collectors.toList()).get(0);
	}

	@Override
	public void purgeAll() {
	medidas = new ArrayList<K>();
		
	}

	@Override
	public void deleteMedida(int id) {
		medidas.remove(getMedida(id));
		
	}

	@Override
	public K getMedida(String _id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMedida(String _id) {
		// TODO Auto-generated method stub
		
	}

}
