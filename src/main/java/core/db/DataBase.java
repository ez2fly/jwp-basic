package core.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

import core.mvc.DispatcherServlet;
import next.dao.UserDao;
import next.model.User;

public class DataBase {
	private static final Logger logger = LoggerFactory.getLogger(DataBase.class);
//	private static Map<String, User> users = Maps.newHashMap();
	private static UserDao users = new UserDao();
	
	static {
//		users.put("admin", new User("admin", "password", "자바지기", "admin@slipp.net"));
	}
	
	public static void addUser(User user) {
//		users.put(user.getUserId(), user);
		try{
			users.insert(user);
		}catch(SQLException e) {
			logger.debug(e.toString());
		}
	}
	
	public static User findUserById(String userId) {
//		return users.get(userId);
		User user = null;
		try {
			user = users.findByUserId(userId);
		}catch(SQLException e) {
			logger.debug(e.toString());
		}
		return user;
	}
	
	public static Collection<User> findAll() {
//		return users.values();
		Collection<User> userCols = new ArrayList<User>();
		try {
			userCols = users.findAll();
		} catch(SQLException e) {
			logger.debug(e.toString());
		}
		return userCols;
	}
	
	public static void updateUser(User user) {
		try{
			users.update(user);
		}catch(SQLException e) {
			logger.debug(e.toString());
		}
	}
	
	public static void deleteUser(String userId) {
		try{
			users.delete(userId);
		}catch(SQLException e) {
			logger.debug(e.toString());
		}
	}
}
