package br.com.fiap.healthtrack.user.data.dao;

import br.com.fiap.healthtrack.user.User;

public interface UserDao {

	User getUserBy_Id(String _id);
	void addUser (User user);
	void updateUser (User user);
	User getUser();
	User getUserByEmail(String email);
	
}
