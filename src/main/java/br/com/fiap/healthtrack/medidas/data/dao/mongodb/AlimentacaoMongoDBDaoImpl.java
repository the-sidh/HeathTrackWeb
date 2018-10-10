package br.com.fiap.healthtrack.medidas.data.dao.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.fiap.healthtrack.database.NoSQLClientManagerMongoDB;
import br.com.fiap.healthtrack.medidas.alimentacao.bson.AlimentacaoBson;
import br.com.fiap.healthtrack.medidas.atividadefisica.bson.AtividadeFisicaBson;

public class AlimentacaoMongoDBDaoImpl extends MedidaMongoDBDao<AlimentacaoBson> {

	@Override MongoCollection<AlimentacaoBson> getCollection() {
		MongoClient mongoClient = NoSQLClientManagerMongoDB.getInstance().getMongoClient();
		MongoDatabase database = mongoClient.getDatabase("HealthTrack");
		MongoCollection<AlimentacaoBson> mongoCollection = database.getCollection("alimentacao", AlimentacaoBson.class);
		return mongoCollection;
	}
}
