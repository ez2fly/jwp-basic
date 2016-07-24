package next.dao;

import core.jdbc.JdbcTemplate;

public abstract class ObjectDao {
	protected JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
}
