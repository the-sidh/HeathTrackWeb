package br.com.fiap.healthtrack.medidas.alimentacao.bson;

import java.util.HashMap;
import java.util.Map;
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

import br.com.fiap.healthtrack.medidas.alimentacao.TipoAlimentacao;

public class TipoAlimentacaoCodec implements CollectibleCodec<TipoAlimentacao> {
	private Codec<Document> documentCodec;
	Document document = new Document();

	public TipoAlimentacaoCodec() {
		this.documentCodec = new DocumentCodec();
	}

	public TipoAlimentacaoCodec(Codec<Document> codec) {
		this.documentCodec = codec;
	}

	@Override
	public void encode(BsonWriter writer, TipoAlimentacao tipoAlimentacao, EncoderContext encoderContext) {
		String id = tipoAlimentacao.get_id();
		String descricao = tipoAlimentacao.getDescricao();
		if (id != null) {
			document.put("_id", id);
		}

		if (descricao != null) {
			document.put("descricao", descricao);
		}

		documentCodec.encode(writer, document, encoderContext);
	}

	@Override
	public Class<TipoAlimentacao> getEncoderClass() {
		return TipoAlimentacao.class;
	}

	@Override
	public TipoAlimentacao decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);

		String descricao = document.getString("descricao");

		Map<String, TipoAlimentacao> map = new HashMap<String, TipoAlimentacao>();
		map.put("Café da manhã", TipoAlimentacao.CAFE_DA_MANHA);
		map.put("Almoço", TipoAlimentacao.ALMOCO);
		map.put("Jantar", TipoAlimentacao.JANTAR);
		map.put("Lanche leve", TipoAlimentacao.LANCHE_LEVE);
		map.put("Fruta", TipoAlimentacao.FRUTA);

		return map.get(descricao);
	}

	@Override
	public TipoAlimentacao generateIdIfAbsentFromDocument(TipoAlimentacao tipoAlimentacao) {
		if (!documentHasId(tipoAlimentacao)) {
			tipoAlimentacao.setId(UUID.randomUUID().toString());
		}
		return tipoAlimentacao;
	}

	@Override
	public boolean documentHasId(TipoAlimentacao alimentacao) {
		return alimentacao.get_id() != null;
	}

	@Override
	public BsonValue getDocumentId(TipoAlimentacao alimentacao) {
		if (!documentHasId(alimentacao)) {
			throw new IllegalStateException("The document does not contain an _id");
		}

		return new BsonString(alimentacao.get_id());
	}

}
