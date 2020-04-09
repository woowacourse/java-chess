package chess.utils.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

import chess.exception.SQLAccessException;
import chess.exception.SQLConnectionException;

public class JDBCTemplate {
	private static final String SERVER = "localhost:13306";
	private static final String DATABASE = "chess";
	private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
	private static final String URL = String.format("jdbc:mysql://%s/%s%s", SERVER, DATABASE, OPTION);
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";

	public <T> Optional<T> executeQuery(String query, RowMapper<T> mapper, Object... params) {
		return execute(preparedStatement -> {
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return Optional.empty();
			}
			return Optional.of(mapper.map(resultSet));
		}, query, params);
	}

	public int executeUpdate(String query, Object... params) {
		return execute(PreparedStatement::executeUpdate, query, params);
	}

	private <T> T execute(FunctionWithException<PreparedStatement, T, SQLException> executor, String query,
		Object... params) {
		try (Connection con = getConnection();
			 PreparedStatement preparedStatement = con.prepareStatement(query)) {
			setPreparedStatementParameter(preparedStatement, params);
			return wrapException(executor).apply(preparedStatement);
		} catch (SQLException e) {
			throw new SQLAccessException();
		}
	}

	private Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
			throw new SQLConnectionException();
		}
	}

	private void setPreparedStatementParameter(PreparedStatement preparedStatement, Object[] params) throws
		SQLException {
		for (int i = 0; i < params.length; i++) {
			preparedStatement.setObject(i + 1, params[i]);
		}
	}

	private <T, R, E extends SQLException> Function<T, R> wrapException(FunctionWithException<T, R, E> function) {
		return t -> {
			try {
				return function.apply(t);
			} catch (SQLException e) {
				throw new SQLAccessException();
			}
		};
	}
}
