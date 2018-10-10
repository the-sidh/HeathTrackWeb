package br.com.fiap.healthtrack.database;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import br.com.fiap.healthtrack.medidas.alimentacao.bson.AlimentacaoCodec;
import br.com.fiap.healthtrack.medidas.alimentacao.bson.TipoAlimentacaoCodec;
import br.com.fiap.healthtrack.medidas.atividadefisica.bson.AtividadeFisicaCodec;
import br.com.fiap.healthtrack.medidas.peso.bson.PesoCodec;
import br.com.fiap.healthtrack.medidas.pressao.bson.PressaoCodec;

public class NoSQLClientManagerMongoDB {

	private static final String ALIMENTACAO = "br.com.fiap.healthtrack.medidas.alimentacao.Alimentacao";
	private static final String ATIVIDADE_FISICA = "br.com.fiap.healthtrack.medidas.atividadefisica.AtividadeFisica";
	private static final String PESO = "br.com.fiap.healthtrack.medidas.peso.Peso";
	private static final String PRESSAO = "br.com.fiap.healthtrack.medidas.pressao.Pressao";

	private static NoSQLClientManagerMongoDB instance;

	public static NoSQLClientManagerMongoDB getInstance() {
		if (instance == null) {
			instance = new NoSQLClientManagerMongoDB();
		}
		return instance;
	}

	MongoClient mongoClient = null;

	public MongoClient getMongoClient() {
		Codec<Document> defaultDocumentCodec = MongoClientSettings.getDefaultCodecRegistry().get(Document.class);
		PesoCodec	pesoCodec = new PesoCodec(defaultDocumentCodec);
		AlimentacaoCodec	alimentacaoCodec = new AlimentacaoCodec(defaultDocumentCodec);
		PressaoCodec pressaoCodec = new PressaoCodec(defaultDocumentCodec);
		AtividadeFisicaCodec atividadeFisicaCodec = new AtividadeFisicaCodec(defaultDocumentCodec);
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromCodecs(pesoCodec,alimentacaoCodec,pressaoCodec,atividadeFisicaCodec));
		MongoClientSettings settings = MongoClientSettings.builder().codecRegistry(codecRegistry).build();
		mongoClient = MongoClients.create(settings);
		return mongoClient;
	}

	public void closeClient() {
		mongoClient.close();

	}

}