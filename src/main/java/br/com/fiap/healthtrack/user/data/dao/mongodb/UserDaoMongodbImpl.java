package br.com.fiap.healthtrack.user.data.dao.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import br.com.fiap.healthtrack.database.NoSQLClientManagerMongoDB;
import br.com.fiap.healthtrack.user.User;
import br.com.fiap.healthtrack.user.bson.UserBson;
import br.com.fiap.healthtrack.user.data.dao.UserDao;

public class UserDaoMongodbImpl implements UserDao {

	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(User user) {
		MongoCollection<UserBson> mongoCollection = getCollection();
		try {
			mongoCollection.insertOne((UserBson) user);
		} finally {
			close();
		}
	}

	@Override
	public void updateUser(User user) {
		MongoCollection<UserBson> mongoCollection = getCollection();
		try {
			mongoCollection.replaceOne(Filters.eq("_id", user.get_id()), (UserBson) user);
		} finally {
			close();
		}
	}

	@Override
	public User getUserByEmail(String email) {
		MongoCollection<UserBson> mongoCollection = getCollection();
		User user = null;
		try {
			user = mongoCollection.find(Filters.eq("email", email)).first();
		} finally {
			close();
		}
		return user;

	}

	@Override
	public User getUserBy_Id(String _id) {
		MongoCollection<UserBson> mongoCollection = getCollection();
		User user = null;
		try {
			user = mongoCollection.find(Filters.eq("_id", _id)).first();
		} finally {
			close();
		}
		return user;
	}
	
	private void close() {
		NoSQLClientManagerMongoDB.getInstance().closeClient();
	}

	private MongoCollection<UserBson> getCollection() {
		MongoClient mongoClient = NoSQLClientManagerMongoDB.getInstance().getMongoClient();
		MongoDatabase database = mongoClient.getDatabase("HealthTrack");
		MongoCollection<UserBson> mongoCollection = database.getCollection("User", UserBson.class);
		return mongoCollection;
	}


}
