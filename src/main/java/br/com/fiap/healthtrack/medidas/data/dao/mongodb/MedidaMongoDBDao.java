package br.com.fiap.healthtrack.medidas.data.dao.mongodb;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import br.com.fiap.healthtrack.database.NoSQLClientManagerMongoDB;
import br.com.fiap.healthtrack.medidas.Medida;
import br.com.fiap.healthtrack.medidas.data.dao.MedidaDao;

public abstract class MedidaMongoDBDao<K extends Medida> implements MedidaDao<K> {

	public MedidaMongoDBDao() {
		super();
	}

	@Override
	public List<K> getListaMedidas() {
		MongoCollection<K> mongoCollection = getCollection();
		List<K> list = new ArrayList<K>();
		try {
			FindIterable<K> find = mongoCollection.find();
			for (K alimentacaoBson : find) {
				list.add(alimentacaoBson);
			}
		} finally {
			close();
		}

		return list;
	}

	@Override
	public List<K> getListaMedidas(int first, int quant) {
		return getListaMedidas().stream().skip(first).limit(quant).collect(Collectors.toList());
	}

	@Override
	public K getMedida(String id) {
		MongoCollection<K> mongoCollection = getCollection();
		K medida = null;
		try {
			medida = mongoCollection.find(Filters.eq("_id", id)).first();
		} finally {
			close();
		}
		return medida;

	}

	@Override
	public void insertMedidas(K medida) {
		MongoCollection<K> mongoCollection = getCollection();
		try {
			mongoCollection.insertOne(medida);
		} finally {
			close();
		}
	}

	@Override
	public void updateMedida(K medida) {
		MongoCollection<K> mongoCollection = getCollection();
		try {
			mongoCollection.replaceOne(Filters.eq("_id", medida.get_id()), medida);
		} finally {
			close();
		}
	}

	@Override
	public void deleteMedida(K medida) {
		MongoCollection<K> mongoCollection = getCollection();
		try {
			mongoCollection.findOneAndDelete(Filters.eq("_id", medida.get_id()));
		} finally {
			close();
		}

	}

	@Override
	public void deleteMedida(int id) {
		MongoCollection<K> mongoCollection = getCollection();
		try {
			mongoCollection.findOneAndDelete(Filters.eq("_id", id));
		} finally {
			close();
		}
	}

	@Override
	public void purgeAll() {
		MongoCollection<K> mongoCollection = getCollection();
		try {
			mongoCollection.drop();
		} finally {
			close();
		}
	}

	private void close() {
		NoSQLClientManagerMongoDB.getInstance().closeClient();
	}

	abstract MongoCollection<K> getCollection();

	@Override
	public K getMedida(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMedida(String _id) {
		// TODO Auto-generated method stub

	}

}
