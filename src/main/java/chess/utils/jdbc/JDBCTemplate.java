package chess.utils.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.exception.SQLAccessException;

public class JDBCTemplate {
	private static final String SERVER = "localhost:13306";
	private static final String DATABASE = "chess";
	private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
	private static final String URL = String.format("jdbc:mysql://%s/%s%s", SERVER, DATABASE, OPTION);
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";

	public <T> T executeQuery(String query, RowMapper<T> mapper, Object... params) {
		try (Connection con = getConnection();
			 PreparedStatement preparedStatement = con.prepareStatement(query)) {
			for (int i = 1; i <= params.length; i++) {
				preparedStatement.setObject(i, params[i]);
			}
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			return mapper.map(resultSet);
		} catch (SQLException e) {
			throw new SQLAccessException();
		}
	}

	public int executeUpdate(String query, Object... params) {
		try (Connection con = getConnection();
			 PreparedStatement preparedStatement = con.prepareStatement(query)) {
			for (int i = 1; i <= params.length; i++) {
				preparedStatement.setObject(i, params[i]);
			}
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLAccessException();
		}
	}

	private Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
			throw new SQLAccessException();
		}
	}
}
