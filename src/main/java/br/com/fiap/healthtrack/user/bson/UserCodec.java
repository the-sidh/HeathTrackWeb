package br.com.fiap.healthtrack.user.bson;

import java.util.Date;
import java.util.UUID;

import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;

import br.com.fiap.healthtrack.user.Genero;

public class UserCodec implements CollectibleCodec<UserBson> {

	private Codec<Document> documentCodec;
	Document document = new Document();

	public UserCodec() {
		this.documentCodec = new DocumentCodec();
	}

	public UserCodec(Codec<Document> codec) {
		this.documentCodec = codec;
	}

	@Override
	public void encode(BsonWriter writer, UserBson user, EncoderContext encoderContext) {
		String nome = user.getNome();
		Date dataNasc = user.getDataNasc();
		Genero genero = user.getGenero();
		Double altura = user.getAltura();
		String email = user.getEmail();
		String password = user.getPassword();

		if (nome != null)
			document.put("nome", nome);
		if (dataNasc != null)
			document.put("dataNasc", dataNasc);
		if (genero != null) {
			if (genero.equals(Genero.FEMININO)) {
				document.put("genero", "feminino");
			} else {
				document.put("genero", "masculino");
			}

		}
		if (altura != null)
			document.put("altura", altura);
		if (email != null)
			document.put("email", email);
		if (password != null)
			document.put("password", password);

	}

	@Override
	public Class<UserBson> getEncoderClass() {
		return UserBson.class;
	}

	@Override
	public UserBson decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);

		String nome = document.getString("nome");
		Date dataNasc = document.getDate("dataNasc");
		Genero genero = document.getString("genero").equals("feminino") ? Genero.FEMININO : Genero.MASCULINO;
		Double altura = document.getDouble("altura");
		String email = document.getString("email");
		String password = document.getString("password");		

		UserBson user = new UserBson();
		
		user.setNome(nome);
		user.setDataNasc(dataNasc);
		user.setGenero(genero);
		user.setAltura(altura);
		user.setEmail(email);
		user.setPassword(password);

		return user;
	}

	@Override
	public boolean documentHasId(UserBson user) {
		return user.get_id() != null;
	}

	@Override
	public UserBson generateIdIfAbsentFromDocument(UserBson user) {
		if (!documentHasId(user)) {
			user.set_id(UUID.randomUUID().toString());
		}
		return user;
	}

	@Override
	public BsonValue getDocumentId(UserBson user) {
		if (!documentHasId(user)) {
			throw new IllegalStateException("The document does not contain an _id");
		}

		return new BsonString(user.get_id());
	}
	

}
