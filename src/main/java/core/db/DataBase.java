package core.db;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

import next.model.User;

public class DataBase {
	private static Map<String, User> users = new HashMap () {{
		this.put("ez2fly", new User("ez2fly", "1234", "김봉남", "ez2fly@naver.com"));
		this.put("kkang18x", new User("kkang18x", "1234", "조광수", "kkang18x@nate.com"));
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
	
	public static void modifyUser(User user) {
		users.replace(user.getUserId(), user);
	}
	
}
