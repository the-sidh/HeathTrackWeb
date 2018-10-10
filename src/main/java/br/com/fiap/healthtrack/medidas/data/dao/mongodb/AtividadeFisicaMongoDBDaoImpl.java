package br.com.fiap.healthtrack.medidas.data.dao.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.fiap.healthtrack.database.NoSQLClientManagerMongoDB;
import br.com.fiap.healthtrack.medidas.atividadefisica.bson.AtividadeFisicaBson;

public class AtividadeFisicaMongoDBDaoImpl extends MedidaMongoDBDao<AtividadeFisicaBson> {

	@Override MongoCollection<AtividadeFisicaBson> getCollection() {
		MongoClient mongoClient = NoSQLClientManagerMongoDB.getInstance().getMongoClient();
		MongoDatabase database = mongoClient.getDatabase("HealthTrack");
		MongoCollection<AtividadeFisicaBson> mongoCollection = database.getCollection("atividade-fisica", AtividadeFisicaBson.class);
		return mongoCollection;
	}
}
