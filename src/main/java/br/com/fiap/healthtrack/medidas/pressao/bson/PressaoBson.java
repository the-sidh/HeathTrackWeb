package br.com.fiap.healthtrack.medidas.pressao.bson;

import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import br.com.fiap.healthtrack.medidas.pressao.Pressao;

public class PressaoBson extends Pressao implements Bson {



	public PressaoBson() {
		super();
	}

	public PressaoBson(int sistolica, int diastolica) {
		super(sistolica, diastolica);
	}

	@Override
	public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
		return new BsonDocumentWrapper(this, codecRegistry.get(PressaoBson.class));
	}

	

}
