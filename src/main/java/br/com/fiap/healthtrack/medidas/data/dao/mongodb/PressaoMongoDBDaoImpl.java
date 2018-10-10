package br.com.fiap.healthtrack.medidas.data.dao.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.fiap.healthtrack.database.NoSQLClientManagerMongoDB;
import br.com.fiap.healthtrack.medidas.alimentacao.bson.AlimentacaoBson;
import br.com.fiap.healthtrack.medidas.atividadefisica.bson.AtividadeFisicaBson;
import br.com.fiap.healthtrack.medidas.peso.bson.PesoBson;
import br.com.fiap.healthtrack.medidas.pressao.bson.PressaoBson;

public class PressaoMongoDBDaoImpl extends MedidaMongoDBDao<PressaoBson> {

	@Override MongoCollection<PressaoBson> getCollection() {
		MongoClient mongoClient = NoSQLClientManagerMongoDB.getInstance().getMongoClient();
		MongoDatabase database = mongoClient.getDatabase("HealthTrack");
		MongoCollection<PressaoBson> mongoCollection = database.getCollection("pressao", PressaoBson.class);
		return mongoCollection;
	}
}
