package db;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


import model.User;

public class DataBase {
//	private static Map<String, User> users = new HashMap();
	
	private static Map<String, User> users = new HashMap() {{
		this.put("ez2fly", new User("ez2fly", "1234", "김봉남", "ez2fly@naver.com"));
		this.put("kkang18x", new User("kkang18x", "1234", "남상칠", "kkang18x@nate.com"));
	}};
	
	public static void addUser(User user) {
		users.put(user.getUserId(), user);
	}
	
	public static User findUserById(String userId) {
		return users.get(userId);
	}
	
	public static Collection<User> findAll() {
		return users.values();
	}
	
	public static int getDataCount() {
		return users.size();
	}
}
