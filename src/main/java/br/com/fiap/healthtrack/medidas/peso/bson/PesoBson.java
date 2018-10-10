package br.com.fiap.healthtrack.medidas.peso.bson;

import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import br.com.fiap.healthtrack.medidas.peso.Peso;

public class PesoBson extends Peso implements Bson {

	
	public PesoBson(float pesoEmKg) {
		super(pesoEmKg);
	}
	public PesoBson() {
		super();
	}
	

	@Override
	public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
		  return new BsonDocumentWrapper(this, codecRegistry.get(PesoBson.class));
	}
	
	
	
	

}
