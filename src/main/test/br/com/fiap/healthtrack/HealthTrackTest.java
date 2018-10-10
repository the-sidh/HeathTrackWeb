package br.com.fiap.healthtrack;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.fiap.healthtrack.database.DBDomain;
import br.com.fiap.healthtrack.medidas.OperacoesMedidasHelper;
import br.com.fiap.healthtrack.medidas.alimentacao.Alimentacao;
import br.com.fiap.healthtrack.medidas.alimentacao.TipoAlimentacao;
import br.com.fiap.healthtrack.medidas.atividadefisica.AtividadeFisica;
import br.com.fiap.healthtrack.medidas.atividadefisica.TipoAtividadeFisica;
import br.com.fiap.healthtrack.medidas.atividadefisica.bson.AtividadeFisicaBson;
import br.com.fiap.healthtrack.medidas.data.dao.MedidaDao;
import br.com.fiap.healthtrack.medidas.data.dao.MedidaDaoFactory;
import br.com.fiap.healthtrack.medidas.data.dao.MedidaDaoType;
import br.com.fiap.healthtrack.medidas.data.dao.MedidaType;
import br.com.fiap.healthtrack.medidas.peso.IMC;
import br.com.fiap.healthtrack.medidas.peso.Peso;
import br.com.fiap.healthtrack.medidas.peso.bson.PesoBson;
import br.com.fiap.healthtrack.medidas.pressao.Pressao;
import br.com.fiap.healthtrack.medidas.pressao.SituacaoPressao;
import br.com.fiap.healthtrack.user.Genero;
import br.com.fiap.healthtrack.user.User;

public class HealthTrackTest {

	private User user;

	@Before
	public void setupUser() {

		user = new User("Pedro Fonseca", new Date(), Genero.MASCULINO, 1.7f);
	}

	@Test
	public void testIMC() {
		Peso peso1 = new Peso(40);
		Peso peso2 = new Peso(46);
		Peso peso3 = new Peso(46.5f);
		Peso peso4 = new Peso(49.4f);
		Peso peso5 = new Peso(52);
		Peso peso6 = new Peso(55);
		Peso peso7 = new Peso(69);
		Peso peso8 = new Peso(75.5f);
		Peso peso9 = new Peso(83);
		Peso peso10 = new Peso(89.5f);
		Peso peso11 = new Peso(98);
		Peso peso12 = new Peso(104.05f);
		Peso peso13 = new Peso(112f);

		IMC IMC1 = OperacoesMedidasHelper.getInstance().getIMC(user.getAltura(), peso1.getPesoEmKg());
		IMC IMC2 = OperacoesMedidasHelper.getInstance().getIMC(user.getAltura(), peso2.getPesoEmKg());
		IMC IMC3 = OperacoesMedidasHelper.getInstance().getIMC(user.getAltura(), peso3.getPesoEmKg());
		IMC IMC4 = OperacoesMedidasHelper.getInstance().getIMC(user.getAltura(), peso4.getPesoEmKg());
		IMC IMC5 = OperacoesMedidasHelper.getInstance().getIMC(user.getAltura(), peso5.getPesoEmKg());
		IMC IMC6 = OperacoesMedidasHelper.getInstance().getIMC(user.getAltura(), peso6.getPesoEmKg());
		IMC IMC7 = OperacoesMedidasHelper.getInstance().getIMC(user.getAltura(), peso7.getPesoEmKg());
		IMC IMC8 = OperacoesMedidasHelper.getInstance().getIMC(user.getAltura(), peso8.getPesoEmKg());
		IMC IMC9 = OperacoesMedidasHelper.getInstance().getIMC(user.getAltura(), peso9.getPesoEmKg());
		IMC IMC10 = OperacoesMedidasHelper.getInstance().getIMC(user.getAltura(), peso10.getPesoEmKg());
		IMC IMC11 = OperacoesMedidasHelper.getInstance().getIMC(user.getAltura(), peso11.getPesoEmKg());
		IMC IMC12 = OperacoesMedidasHelper.getInstance().getIMC(user.getAltura(), peso12.getPesoEmKg());
		IMC IMC13 = OperacoesMedidasHelper.getInstance().getIMC(user.getAltura(), peso13.getPesoEmKg());

		assertTrue(IMC1.equals(IMC.MAGREZA_GRAVE));
		assertTrue(IMC2.equals(IMC.MAGREZA_GRAVE));
		assertTrue(IMC3.equals(IMC.MAGREZA_MODERADA));
		assertTrue(IMC4.equals(IMC.MAGREZA_LEVE));
		assertTrue(IMC5.equals(IMC.MAGREZA_LEVE));
		assertTrue(IMC6.equals(IMC.SAUDAVEL));
		assertTrue(IMC7.equals(IMC.SAUDAVEL));
		assertTrue(IMC8.equals(IMC.SOBREPESO));
		assertTrue(IMC9.equals(IMC.SOBREPESO));
		assertTrue(IMC10.equals(IMC.OBESIDADE_1));
		assertTrue(IMC11.equals(IMC.OBESIDADE_1));
		assertTrue(IMC12.equals(IMC.OBESIDADE_2));
		assertTrue(IMC13.equals(IMC.OBESIDADE_2));

	}

	@Test
	public void testSituacaoPressao() {
		Pressao p1 = new Pressao(141, 91);// alta
		Pressao p2 = new Pressao(130, 91);// alta
		Pressao p3 = new Pressao(141, 85);// alta

		Pressao p4 = new Pressao(130, 81);// normal

		Pressao p5 = new Pressao(119, 75);// baixa
		Pressao p6 = new Pressao(130, 75);// baixa
		Pressao p7 = new Pressao(119, 81);// baixa

		assertTrue(SituacaoPressao.ELEVADA.equals(
				OperacoesMedidasHelper.getInstance().getSituacaoPressao(p1.getSistolica(), p1.getDiastolica())));
		assertTrue(SituacaoPressao.ELEVADA.equals(
				OperacoesMedidasHelper.getInstance().getSituacaoPressao(p2.getSistolica(), p2.getDiastolica())));
		assertTrue(SituacaoPressao.ELEVADA.equals(
				OperacoesMedidasHelper.getInstance().getSituacaoPressao(p3.getSistolica(), p3.getDiastolica())));

		assertTrue(SituacaoPressao.NORMAL.equals(
				OperacoesMedidasHelper.getInstance().getSituacaoPressao(p4.getSistolica(), p4.getDiastolica())));

		assertTrue(SituacaoPressao.ABAIXO_DO_NORMAL.equals(
				OperacoesMedidasHelper.getInstance().getSituacaoPressao(p5.getSistolica(), p5.getDiastolica())));
		assertTrue(SituacaoPressao.ABAIXO_DO_NORMAL.equals(
				OperacoesMedidasHelper.getInstance().getSituacaoPressao(p6.getSistolica(), p6.getDiastolica())));
		assertTrue(SituacaoPressao.ABAIXO_DO_NORMAL.equals(
				OperacoesMedidasHelper.getInstance().getSituacaoPressao(p7.getSistolica(), p7.getDiastolica())));
	}

	@Test
	public void testAlimentacao() {
		Alimentacao a = new Alimentacao(TipoAlimentacao.ALMOCO, 100, "");
		assertTrue("Almoço".equals(a.getTipo().getDescricao()));
	}

	@Test
	public void testPesoDaoJDBC() {

		Peso peso1 = new Peso(40);
		peso1.setDate(new Date());
		
		Peso peso2 = new Peso(46);
		peso2.setDate(new Date());
		
		Peso peso3 = new Peso(46.5f);
		peso3.setDate(new Date());
		
		Peso peso4 = new Peso(49.4f);
		peso4.setDate(new Date());
		
		Peso peso5 = new Peso(52);
		peso5.setDate(new Date());
		
		Peso peso6 = new Peso(55);
		peso6.setDate(new Date());
		
		Peso peso7 = new Peso(69);
		peso7.setDate(new Date());
		
		Peso peso8 = new Peso(75.5f);
		peso8.setDate(new Date());
		
		Peso peso9 = new Peso(83);
		peso9.setDate(new Date());
		
		Peso peso10 = new Peso(89.5f);
		peso10.setDate(new Date());

		MedidaDaoType tipoPesoTeste = new MedidaDaoType(MedidaType.PESO, DBDomain.JDBC);
		MedidaDao<Peso> dao = (MedidaDao<Peso>) MedidaDaoFactory.getInstance().getMedidaDao(tipoPesoTeste);

		dao.purgeAll();
		
		dao.insertMedidas(peso1);
		dao.insertMedidas(peso2);
		dao.insertMedidas(peso3);
		dao.insertMedidas(peso4);
		dao.insertMedidas(peso5);
		dao.insertMedidas(peso6);
		dao.insertMedidas(peso7);
		dao.insertMedidas(peso8);
		dao.insertMedidas(peso9);
		dao.insertMedidas(peso10);		

		List<Peso> listaMedidas = dao.getListaMedidas();
		
		for(Peso p : listaMedidas) {
			System.out.println("Peso: "+ p.getPesoEmKg()+" data: "+p.getDate());
		}
		
		assertTrue(listaMedidas.size() == 10);
		assertTrue(dao.getListaMedidas(1, 2).size() == 2);
		listaMedidas.get(0).setPesoEmKg(50f);
		dao.updateMedida(listaMedidas.get(0));
		listaMedidas = dao.getListaMedidas();
		assertTrue(dao.getMedida(listaMedidas.get(0).getId()).getPesoEmKg() == 50f);
		dao.deleteMedida(listaMedidas.get(0));
		listaMedidas = dao.getListaMedidas();
		assertTrue(listaMedidas.size() == 9);

	}

	@Test
	public void testaAtividadeFisicaDaoJDBC() {
		AtividadeFisica af1 = new AtividadeFisica(TipoAtividadeFisica.CAMINHADA, 100, "Teste");
		AtividadeFisica af2 = new AtividadeFisica(TipoAtividadeFisica.CORRIDA, 100, "Teste");
		AtividadeFisica af3 = new AtividadeFisica(TipoAtividadeFisica.PEDALADA, 100, "Teste");

		af1.setDate(new Date());
		af2.setDate(new Date());
		af3.setDate(new Date());
		
		MedidaDaoType tipoPesoTeste = new MedidaDaoType(MedidaType.ATIVIDADE_FISICA, DBDomain.JDBC);
		MedidaDao<AtividadeFisica> dao = (MedidaDao<AtividadeFisica>) MedidaDaoFactory.getInstance().getMedidaDao(tipoPesoTeste);
		
		dao.purgeAll();
		
		dao.insertMedidas(af1);
		dao.insertMedidas(af2);
		dao.insertMedidas(af3);

		List<AtividadeFisica> listaMedidas = dao.getListaMedidas();
		assertTrue(listaMedidas.size() == 3);
		assertTrue(dao.getListaMedidas(1, 2).size() == 2);
		listaMedidas.get(0).setTipo(TipoAtividadeFisica.MUSCULACAO);
		dao.updateMedida(listaMedidas.get(0));
		listaMedidas = dao.getListaMedidas();
		assertTrue(listaMedidas.get(0).getTipo().equals(TipoAtividadeFisica.MUSCULACAO));
		dao.deleteMedida(listaMedidas.get(0));
		listaMedidas = dao.getListaMedidas();
		assertTrue(listaMedidas.size() == 2);

	}
	
	
	@Test
	public void testPesoDaoNosql() {

		PesoBson peso1 = new PesoBson(40);
		peso1.setDate(new Date());
		
		PesoBson peso2 = new PesoBson(46);
		peso2.setDate(new Date());
		
		PesoBson peso3 = new PesoBson(46.5f);
		peso3.setDate(new Date());
		
		PesoBson peso4 = new PesoBson(49.4f);
		peso4.setDate(new Date());
		
		PesoBson peso5 = new PesoBson(52);
		peso5.setDate(new Date());
		
		PesoBson peso6 = new PesoBson(55);
		peso6.setDate(new Date());
		
		PesoBson peso7 = new PesoBson(69);
		peso7.setDate(new Date());
		
		PesoBson peso8 = new PesoBson(75.5f);
		peso8.setDate(new Date());
		
		PesoBson peso9 = new PesoBson(83);
		peso9.setDate(new Date());
		
		PesoBson peso10 = new PesoBson(89.5f);
		peso10.setDate(new Date());

		MedidaDaoType tipoPesoTeste = new MedidaDaoType(MedidaType.PESO, DBDomain.NOSQL);
		MedidaDao<PesoBson> dao = (MedidaDao<PesoBson>) MedidaDaoFactory.getInstance().getMedidaDao(tipoPesoTeste);

		dao.purgeAll();
		
		dao.insertMedidas(peso1);
		dao.insertMedidas(peso2);
		dao.insertMedidas(peso3);
		dao.insertMedidas(peso4);
		dao.insertMedidas(peso5);
		dao.insertMedidas(peso6);
		dao.insertMedidas(peso7);
		dao.insertMedidas(peso8);
		dao.insertMedidas(peso9);
		dao.insertMedidas(peso10);		

		List<PesoBson> listaMedidas = dao.getListaMedidas();
		
		for(PesoBson p : listaMedidas) {
			System.out.println("Peso: "+ p.getPesoEmKg()+" data: "+p.getDate());
		}
		
		assertTrue(listaMedidas.size() == 10);
		assertTrue(dao.getListaMedidas(1, 2).size() == 2);
		listaMedidas.get(0).setPesoEmKg(50f);
		dao.updateMedida(listaMedidas.get(0));
		listaMedidas = dao.getListaMedidas();
		assertTrue(dao.getMedida(listaMedidas.get(0).get_id()).getPesoEmKg() == 50f);
		dao.deleteMedida(listaMedidas.get(0));
		listaMedidas = dao.getListaMedidas();
		assertTrue(listaMedidas.size() == 9);

	}
	
	@Test
	public void testaAtividadeFisicaDaoNosql() {
		AtividadeFisicaBson af1 = new AtividadeFisicaBson(TipoAtividadeFisica.CAMINHADA, 100, "Teste");
		AtividadeFisicaBson af2 = new AtividadeFisicaBson(TipoAtividadeFisica.CORRIDA, 100, "Teste");
		AtividadeFisicaBson af3 = new AtividadeFisicaBson(TipoAtividadeFisica.PEDALADA, 100, "Teste");

		af1.setDate(new Date());
		af2.setDate(new Date());
		af3.setDate(new Date());
		
		MedidaDaoType tipoPesoTeste = new MedidaDaoType(MedidaType.ATIVIDADE_FISICA, DBDomain.NOSQL);
		MedidaDao<AtividadeFisicaBson> dao = (MedidaDao<AtividadeFisicaBson>) MedidaDaoFactory.getInstance().getMedidaDao(tipoPesoTeste);
		
		dao.purgeAll();
		
		dao.insertMedidas(af1);
		dao.insertMedidas(af2);
		dao.insertMedidas(af3);

		List<AtividadeFisicaBson> listaMedidas = dao.getListaMedidas();
		assertTrue(listaMedidas.size() == 3);
		assertTrue(dao.getListaMedidas(1, 2).size() == 2);
		listaMedidas.get(0).setTipo(TipoAtividadeFisica.MUSCULACAO);
		dao.updateMedida(listaMedidas.get(0));
		listaMedidas = dao.getListaMedidas();
		assertTrue(listaMedidas.get(0).getTipo().equals(TipoAtividadeFisica.MUSCULACAO));
		dao.deleteMedida(listaMedidas.get(0));
		listaMedidas = dao.getListaMedidas();
		assertTrue(listaMedidas.size() == 2);

	}
	
//	@Test
//	public void testUserDao(){
//		User user = new User("Luis", new Date(), Genero.MASCULINO, 180f);
//		user.setEmail("teste@teste.com");
//		user.setPassword("secret");
//		
//		UserDao dao = UserDaoFactory.getInstance().getDao(UserDaoDomain.TESTE);
//		dao.addUser(user);
//		User user1 = dao.getUser();
//		user1.setPassword("secret1");
//		dao.updateUser(user1);
//		assertTrue(dao.getUser().getPassword().equals("secret1"));
//		
//	}

}
