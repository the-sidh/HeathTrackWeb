package br.com.fiap.healthtrack.user.bson;

import java.util.Date;

import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import br.com.fiap.healthtrack.user.Genero;
import br.com.fiap.healthtrack.user.User;

public class UserBson extends User implements Bson {

	public UserBson(String nome, Date dataNasc, Genero genero, float altura) {
		super(nome, dataNasc, genero, altura);
		// TODO Auto-generated constructor stub
	}
	
	

	public UserBson() {
		super();
	}



	@Override
	public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
		return new BsonDocumentWrapper(this, codecRegistry.get(UserBson.class));
	}
}
