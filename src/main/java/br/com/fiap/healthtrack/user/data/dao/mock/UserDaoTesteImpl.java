package br.com.fiap.healthtrack.user.data.dao.mock;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.healthtrack.user.User;
import br.com.fiap.healthtrack.user.data.dao.UserDao;

public class UserDaoTesteImpl implements UserDao {

	List<User> users = new ArrayList<User>();
	
	@Override
	public User getUser() {
	
		return users.get(0);
	}

	@Override
	public void addUser(User user) {
		users.add(user);
		
	}

	@Override
	public void updateUser(User user) {
		users.set(users.indexOf(user), user);
		
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserBy_Id(String _id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
