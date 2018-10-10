package br.com.fiap.healthtrack.medidas.peso.bson;

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

public class PesoCodec implements CollectibleCodec<PesoBson> {
	private Codec<Document> documentCodec;
	Document document = new Document();

	public PesoCodec() {
		this.documentCodec = new DocumentCodec();
	}

	public PesoCodec(Codec<Document> codec) {
		this.documentCodec = codec;
	}

	@Override
	public void encode(BsonWriter writer, PesoBson peso, EncoderContext encoderContext) {
		String id = peso.get_id();
		Double pesoEmKg = peso.getPesoEmKg();
		Date data = peso.getDate();
		if (id != null) {
			document.put("_id", id);
		}

		if (pesoEmKg != null) {
			document.put("pesoEmKg", pesoEmKg);
		}

		if (data != null) {
			document.put("data", data);
		}

		documentCodec.encode(writer, document, encoderContext);
	}

	@Override
	public Class<PesoBson> getEncoderClass() {
		return PesoBson.class;
	}

	@Override
	public PesoBson decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);

		PesoBson peso = new PesoBson();

		String id = document.getString("_id");
		Double pesoEmKg = (Double) document.get("pesoEmKg");
		Date data = document.getDate("data");

		peso.setDate(data);
		peso.set_id(id);
		peso.setPesoEmKg(pesoEmKg);

		return peso;
	}

	@Override
	public PesoBson generateIdIfAbsentFromDocument(PesoBson peso) {
		if (!documentHasId(peso)) {
			peso.set_id(UUID.randomUUID().toString());
		}
		return peso;
	}

	@Override
	public boolean documentHasId(PesoBson peso) {
		return peso.get_id() != null;
	}

	@Override
	public BsonValue getDocumentId(PesoBson peso) {
		if (!documentHasId(peso)) {
			throw new IllegalStateException("The document does not contain an _id");
		}

		return new BsonString(peso.get_id());
	}

}
