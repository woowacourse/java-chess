package chess.repository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JdbcTemplate {

	private static final JdbcConnector connectionManager = new JdbcConnector();

	public long executeUpdate(final String query, final List<Object> parameters) {
		try (
			final Connection connection = connectionManager.getConnection();
			final PreparedStatement preparedStatement = connection.prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS)
		) {
			for (int i = 1; i <= parameters.size(); i++) {
				preparedStatement.setObject(i, parameters.get(i - 1));
			}
			int affectedRows = preparedStatement.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("execution 실패. 영향받은 행이 없습니다.");
			}
			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getLong(1);
				}
				else {
					throw new SQLException("영향 받은 행의 첫 PK 반환에 실패했습니다.");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void executeUpdateForDelete(final String query, final List<Object> parameters) {
		try (
			final Connection connection = connectionManager.getConnection();
			final PreparedStatement preparedStatement = connection.prepareStatement(query)
		) {
			for (int i = 1; i <= parameters.size(); i++) {
				preparedStatement.setObject(i, parameters.get(i - 1));
			}
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public <T> T executeQuery(final String query, final List<Object> parameters, final RowMapper<T> rowMapper) {
		try (
			final Connection connection = connectionManager.getConnection();
			final PreparedStatement preparedStatement = connection.prepareStatement(query)
		) {
			for (int i = 1; i <= parameters.size(); i++) {
				preparedStatement.setObject(i, parameters.get(i - 1));
			}
			final ResultSet resultSet = preparedStatement.executeQuery();
			return rowMapper.collectTo(resultSet);
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}
}
