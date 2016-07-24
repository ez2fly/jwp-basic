package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import next.model.Question;
import next.model.User;
import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class QuestionDao extends ObjectDao {
	public List<Question> findAll() {
		String sql = "SELECT questionId, writer, title, createdDate, countOfAnswer FROM QUESTIONS "
				+ "order by questionId desc";
		
		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"),
						rs.getString("writer"), rs.getString("title"), null,
						rs.getTimestamp("createdDate"),
						rs.getInt("countOfAnswer"));
			}
			
		};
		
		return jdbcTemplate.query(sql, rm);
	}

	public Question findById(long questionId) {
		String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS "
				+ "WHERE questionId = ?";
		
		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"),
						rs.getString("writer"), rs.getString("title"),
						rs.getString("contents"),
						rs.getTimestamp("createdDate"),
						rs.getInt("countOfAnswer"));
			}
		};
		
		return jdbcTemplate.queryForObject(sql, rm, questionId);
	}
	
	public void insert(Question newQuestion) {
        String sql = "INSERT INTO QUESTIONS(writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, CURRENT_TIMESTAMP(), 0)";
        jdbcTemplate.update(sql, newQuestion.getWriter()
        			,newQuestion.getTitle()
        			,newQuestion.getContents());
    }
	
	public void updateCountOfAnswer(long questionId, int countOfAnswer) {
        String sql = "UPDATE QUESTIONS SET countOfAnswer=? WHERE questionId=?";
        jdbcTemplate.update(sql, countOfAnswer, questionId);
	}
	
	public void update(long questionId, String title, String contents) {
		String sql = "UPDATE QUESTIONS SET title=?, contents=? WHERE questionId=?";
		jdbcTemplate.update(sql, title, contents, questionId);
	}
	
	public void delete(long questionId) {
		String sql = "DELETE QUESTIONS WHERE questionId=?";
		jdbcTemplate.update(sql, questionId);
	}
}
