package br.com.fiap.healthtrack.medidas.pressao.bson;

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

public class PressaoCodec implements CollectibleCodec<PressaoBson> {
	private Codec<Document> documentCodec;
	Document document = new Document();

	public PressaoCodec() {
		this.documentCodec = new DocumentCodec();
	}

	public PressaoCodec(Codec<Document> codec) {
		this.documentCodec = codec;
	}

	@Override
	public void encode(BsonWriter writer, PressaoBson pressao, EncoderContext encoderContext) {
		String id = pressao.get_id();
		Integer sist = pressao.getSistolica();
		Integer diast = pressao.getDiastolica();

		Date data = pressao.getDate();
		if (id != null) {
			document.put("_id", id);
		}

		if (sist != null) {
			document.put("sist", sist);
		}

		if (diast != null) {
			document.put("diast", diast);
		}
		
		if (data != null) {
			document.put("data", data);
		}

		documentCodec.encode(writer, document, encoderContext);
	}

	@Override
	public Class<PressaoBson> getEncoderClass() {
		return PressaoBson.class;
	}

	@Override
	public PressaoBson decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);

		PressaoBson pressao = new PressaoBson();

		String id = document.getString("_id");
		Date data = document.getDate("data");

		pressao.setDate(data);
		pressao.set_id(id);
		int diastolica =  document.getInteger("diast");
		pressao.setDiastolica(diastolica);
		int sistolica = document.getInteger("sist");
		pressao.setSistolica(sistolica );
		return pressao;
	}

	@Override
	public PressaoBson generateIdIfAbsentFromDocument(PressaoBson pressao) {
		if (!documentHasId(pressao)) {
			pressao.set_id(UUID.randomUUID().toString());
		}
		return pressao;
	}

	@Override
	public boolean documentHasId(PressaoBson pressao) {
		return pressao.get_id() != null;
	}

	@Override
	public BsonValue getDocumentId(PressaoBson pressao) {
		if (!documentHasId(pressao)) {
			throw new IllegalStateException("The document does not contain an _id");
		}

		return new BsonString(pressao.get_id());
	}

}
