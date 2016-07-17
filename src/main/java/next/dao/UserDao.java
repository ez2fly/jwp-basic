package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import next.model.User;

public class UserDao {
	private JdbcTemplate<User> jdbc = new JdbcTemplate<User>();
	
	public void insert(User user) throws SQLException {
		
		PreparedStatementSetter setter=(PreparedStatement pstmt) -> {
			if (user != null && pstmt != null) {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}
		};
		
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		jdbc.update(sql, setter);
	}
	
	public void update(User user) throws SQLException {
		PreparedStatementSetter setter = (PreparedStatement pstmt) ->{
			if (user != null && pstmt != null) {
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getUserId());
			}
		};
		
		
		String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
		jdbc.update(sql, setter);
	}
	
	public void delete(String userId) throws SQLException {
		PreparedStatementSetter setter = (PreparedStatement pstmt) ->{
			if (pstmt != null) {
				pstmt.setString(1, userId);
			}
		};
		
		
		String sql = "DELETE USERS  WHERE userId=?";
		jdbc.update(sql, setter);
		
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
		PreparedStatementSetter setter = (PreparedStatement pstmt) ->{
			if (pstmt != null) {
				pstmt.setString(1, userId);
			}
		};
		
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
		User user = jdbc.queryForObject(sql, setter, rowMapper);
		return user;
	}
	
	
}
