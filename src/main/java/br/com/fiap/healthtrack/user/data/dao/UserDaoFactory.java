package br.com.fiap.healthtrack.user.data.dao;

import br.com.fiap.healthtrack.database.DBDomain;
import br.com.fiap.healthtrack.user.data.dao.mock.UserDaoTesteImpl;
import br.com.fiap.healthtrack.user.data.dao.mongodb.UserDaoMongodbImpl;

public class UserDaoFactory {

	private static UserDaoFactory instance;

	public static UserDaoFactory getInstance() {
		if (instance == null) {
			instance = new UserDaoFactory();
		}
		return instance;
	}

	public UserDao getDao(DBDomain domain) {
		if (domain.equals(DBDomain.TESTE))
			return new UserDaoTesteImpl();
		if (domain.equals(DBDomain.TESTE))	
			return new UserDaoMongodbImpl();
		return null;
	}

}
