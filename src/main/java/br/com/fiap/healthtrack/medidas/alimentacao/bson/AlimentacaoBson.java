package br.com.fiap.healthtrack.medidas.alimentacao.bson;

import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import br.com.fiap.healthtrack.medidas.alimentacao.Alimentacao;
import br.com.fiap.healthtrack.medidas.alimentacao.TipoAlimentacao;
import br.com.fiap.healthtrack.medidas.peso.bson.PesoBson;

public class AlimentacaoBson extends Alimentacao implements Bson {


	public AlimentacaoBson(TipoAlimentacao tipo, int calorias, String descricao) {
		super(tipo, calorias, descricao);
	}

	public AlimentacaoBson() {
		super();
	}

	@Override
	public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
		return new BsonDocumentWrapper(this, codecRegistry.get(AlimentacaoBson.class));
	}


}
