package br.com.fiap.healthtrack.medidas.atividadefisica.bson;

import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import br.com.fiap.healthtrack.medidas.alimentacao.Alimentacao;
import br.com.fiap.healthtrack.medidas.alimentacao.TipoAlimentacao;
import br.com.fiap.healthtrack.medidas.atividadefisica.AtividadeFisica;
import br.com.fiap.healthtrack.medidas.atividadefisica.TipoAtividadeFisica;
import br.com.fiap.healthtrack.medidas.peso.bson.PesoBson;

public class AtividadeFisicaBson extends AtividadeFisica implements Bson {


	public AtividadeFisicaBson(TipoAtividadeFisica tipo, int calorias, String descricao) {
		super(tipo, calorias, descricao);
	}

	public AtividadeFisicaBson() {
		super();
	}

	@Override
	public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
		return new BsonDocumentWrapper(this, codecRegistry.get(AtividadeFisicaBson.class));
	}


}
