package br.com.fiap.healthtrack.medidas.data.dao;

import java.util.List;

import br.com.fiap.healthtrack.medidas.Medida;
import br.com.fiap.healthtrack.medidas.alimentacao.bson.AlimentacaoBson;

public interface MedidaDao<K extends Medida> {

	List<K> getListaMedidas();
	List<K> getListaMedidas(int first, int quant);
	K getMedida(int id);
	void insertMedidas(K medida);
	void updateMedida(K medida);
	void deleteMedida(K medida);
	void deleteMedida(int id);
	void purgeAll();
	K getMedida(String _id);
	void deleteMedida(String _id);
}
