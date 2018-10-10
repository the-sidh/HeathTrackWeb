package br.com.fiap.healthtrack.medidas.atividadefisica.bson;

import java.util.Date;
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

import br.com.fiap.healthtrack.medidas.atividadefisica.TipoAtividadeFisica;

public class AtividadeFisicaCodec implements CollectibleCodec<AtividadeFisicaBson> {
	private Codec<Document> documentCodec;
	Document document = new Document();

	public AtividadeFisicaCodec() {
		this.documentCodec = new DocumentCodec();
	}

	public AtividadeFisicaCodec(Codec<Document> codec) {
		this.documentCodec = codec;
	}

	@Override
	public void encode(BsonWriter writer, AtividadeFisicaBson atFis, EncoderContext encoderContext) {
		String id = atFis.get_id();
		Integer calorias = atFis.getCalorias();
		TipoAtividadeFisica tipoAtivFis = (TipoAtividadeFisica) atFis.getTipo();
		Date date = atFis.getDate();
		String descricao = atFis.getDescricao();
		if (id != null) {
			document.put("_id", id);
		}

		if (calorias != null) {
			document.put("calorias", calorias);
		}
		String descAtividadeFisica = tipoAtivFis.getDescricao();
		if (descAtividadeFisica != null) {
			document.put("tipoAtividadeFisica", descAtividadeFisica);
		}
		if (date != null) {
			document.put("date", date);
		}

		if (descricao != null) {
			document.put("descricao", descricao);
		}


		documentCodec.encode(writer, document, encoderContext);
	}

	@Override
	public Class<AtividadeFisicaBson> getEncoderClass() {
		return AtividadeFisicaBson.class;
	}

	@Override
	public AtividadeFisicaBson decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);

		AtividadeFisicaBson atFis = new AtividadeFisicaBson();

		String id = document.getString("_id");
		Date data = document.getDate("data");
		String descTipoAlimentacao =  document.getString("tipoAtividadeFisica");

		Map<String, TipoAtividadeFisica> map = new HashMap<String, TipoAtividadeFisica>();

		map.put("Caminhada", TipoAtividadeFisica.CAMINHADA);
		map.put("Corrida", TipoAtividadeFisica.CORRIDA);
		map.put("Musculação", TipoAtividadeFisica.MUSCULACAO);
		map.put("Pedalada", TipoAtividadeFisica.PEDALADA);
		
		TipoAtividadeFisica tipoAlimentacao = map.get(descTipoAlimentacao) ;

		Integer calorias = document.getInteger("calorias");
		String descricao = document.getString("descricao");

		atFis.setDate(data);
		atFis.set_id(id);
		atFis.setCalorias(calorias);
		atFis.setDescricao(descricao);
		atFis.setTipo((TipoAtividadeFisica) tipoAlimentacao);

		return atFis;
	}

	@Override
	public AtividadeFisicaBson generateIdIfAbsentFromDocument(AtividadeFisicaBson alimentacao) {
		if (!documentHasId(alimentacao)) {
			alimentacao.set_id(UUID.randomUUID().toString());
		}
		return alimentacao;
	}

	@Override
	public boolean documentHasId(AtividadeFisicaBson alimentacao) {
		return alimentacao.get_id() != null;
	}

	@Override
	public BsonValue getDocumentId(AtividadeFisicaBson alimentacao) {
		if (!documentHasId(alimentacao)) {
			throw new IllegalStateException("The document does not contain an _id");
		}

		return new BsonString(alimentacao.get_id());
	}

}
