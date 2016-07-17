package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;

public class JdbcTemplate<T> {
	public void update(String sql, PreparedStatementSetter pstmtSetter) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmtSetter.values(pstmt);

			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			if (con != null) {
				con.close();
			}
		}
	}
	
	public void update(String sql, Object... values) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < values.length; i++) {
				pstmt.setObject(i + 1, values[i]);
			}

			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			if (con != null) {
				con.close();
			}
		}
	}
	
	public List<T> query(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws SQLException {
		List<T> objs = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmtSetter.values(pstmt);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				objs.add(rowMapper.mapRow(rs));
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		
		return objs;
	}
	
	public List<T> query(String sql, RowMapper<T> rowMapper, Object... values) throws SQLException {
		List<T> objs = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < values.length; i++) {
				pstmt.setObject(i + 1, values[i]);
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				objs.add(rowMapper.mapRow(rs));
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		
		return objs;
	}
	
	public T queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmtSetter.values(pstmt);

			rs = pstmt.executeQuery();

			T obj = null;
			if (rs.next()) {
				obj = rowMapper.mapRow(rs);
			}

			return obj;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
	
	public T queryForObject(String sql, RowMapper<T> rowMapper, Object... values) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < values.length; i++) {
				pstmt.setObject(i + 1, values[i]);
			}

			rs = pstmt.executeQuery();

			T obj = null;
			if (rs.next()) {
				obj = rowMapper.mapRow(rs);
			}

			return obj;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
}
