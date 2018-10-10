package br.com.fiap.healthtrack.medidas.alimentacao.bson;

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

import com.mongodb.MongoClientSettings;

import br.com.fiap.healthtrack.medidas.alimentacao.TipoAlimentacao;

public class AlimentacaoCodec implements CollectibleCodec<AlimentacaoBson> {
	private Codec<Document> documentCodec;
	Document document = new Document();

	public AlimentacaoCodec() {
		this.documentCodec = new DocumentCodec();
	}

	public AlimentacaoCodec(Codec<Document> codec) {
		this.documentCodec = codec;
	}

	@Override
	public void encode(BsonWriter writer, AlimentacaoBson alimentacao, EncoderContext encoderContext) {
		String id = alimentacao.get_id();
		Integer calorias = alimentacao.getCalorias();
		TipoAlimentacao tipoAlimentacao = (TipoAlimentacao) alimentacao.getTipo();
		Date date = alimentacao.getDate();
		String descricao = alimentacao.getDescricao();
		if (id != null) {
			document.put("_id", id);
		}

		if (calorias != null) {
			document.put("calorias", calorias);
		}
		String descricaoTipoAlimentacao = tipoAlimentacao.getDescricao();
		if (descricaoTipoAlimentacao != null) {
			document.put("tipoAlimentacao", descricaoTipoAlimentacao);
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
	public Class<AlimentacaoBson> getEncoderClass() {
		return AlimentacaoBson.class;
	}

	@Override
	public AlimentacaoBson decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = documentCodec.decode(reader, decoderContext);

		AlimentacaoBson alimentacao = new AlimentacaoBson();

		String id = document.getString("_id");
		Date data = document.getDate("data");
		Codec<Document> defaultDocumentCodec = MongoClientSettings.getDefaultCodecRegistry().get(Document.class);
		TipoAlimentacaoCodec	tipoAlimentacaoCodec = new TipoAlimentacaoCodec(defaultDocumentCodec);	
		
		String descTipoAlimentacao =  document.getString("tipoAlimentacao");

		Map<String, TipoAlimentacao> map = new HashMap<String, TipoAlimentacao>();
		map.put("Café da manhã", TipoAlimentacao.CAFE_DA_MANHA);
		map.put("Almoço", TipoAlimentacao.ALMOCO);
		map.put("Jantar", TipoAlimentacao.JANTAR);
		map.put("Lanche leve", TipoAlimentacao.LANCHE_LEVE);
		map.put("Fruta", TipoAlimentacao.FRUTA);
		
		TipoAlimentacao tipoAlimentacao = map.get(descTipoAlimentacao) ;

		Integer calorias = document.getInteger("calorias");
		String descricao = document.getString("descricao");

		alimentacao.setDate(data);
		alimentacao.set_id(id);
		alimentacao.setCalorias(calorias);
		alimentacao.setDescricao(descricao);
		alimentacao.setTipo((TipoAlimentacao) tipoAlimentacao);

		return alimentacao;
	}

	@Override
	public AlimentacaoBson generateIdIfAbsentFromDocument(AlimentacaoBson alimentacao) {
		if (!documentHasId(alimentacao)) {
			alimentacao.set_id(UUID.randomUUID().toString());
		}
		return alimentacao;
	}

	@Override
	public boolean documentHasId(AlimentacaoBson alimentacao) {
		return alimentacao.get_id() != null;
	}

	@Override
	public BsonValue getDocumentId(AlimentacaoBson alimentacao) {
		if (!documentHasId(alimentacao)) {
			throw new IllegalStateException("The document does not contain an _id");
		}

		return new BsonString(alimentacao.get_id());
	}

}
