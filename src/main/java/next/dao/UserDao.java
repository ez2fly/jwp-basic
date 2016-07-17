package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import next.model.User;

public class UserDao {
	private JdbcTemplate<User> jdbc = new JdbcTemplate<User>();
	
	public void insert(User user) throws SQLException {
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		jdbc.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}
	
	public void update(User user) throws SQLException {
		String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
		jdbc.update(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
	}
	
	public void delete(String userId) throws SQLException {
		String sql = "DELETE USERS  WHERE userId=?";
		jdbc.update(sql, userId);
		
	}
	
	public List<User> findAll() throws SQLException {
		// TODO 구현 필요함.
		RowMapper<User> rowMapper = (ResultSet rs)->{
			if (rs != null) {
				return new User(
						rs.getString("userId"), 
						rs.getString("password"), 
						rs.getString("name"),
						rs.getString("email"));
			}
			return null;
		};
		
		String sql = "SELECT userId, password, name, email FROM USERS";
		List<User> users = jdbc.query(sql, rowMapper);
		
		return users;
	}

	public User findByUserId(String userId) throws SQLException {
		RowMapper<User> rowMapper = (ResultSet rs)->{
			if (rs != null) {
				return new User(
						rs.getString("userId"), 
						rs.getString("password"), 
						rs.getString("name"),
						rs.getString("email"));
			}
			return null;
		};
		
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
		User user = jdbc.queryForObject(sql, rowMapper, userId);
		return user;
	}
	
	
}
