package chess.web.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTemplate {

	private final ConnectionManager connectionManager;

	public JdbcTemplate(final ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	public <T> T executeQuery(String query, RowMapper<T> rowMapper, PreparedStatementSetter preparedStatementSetter) {
		try (Connection connection = connectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query)
		) {
			preparedStatementSetter.setArgument(preparedStatement);
			return rowMapper.mapRow(preparedStatement.executeQuery());
		} catch (SQLException exception) {
			System.err.println(exception.getMessage());
		}
		return null;
	}

	public <T> T executeQuery(String query, RowMapper<T> rowMapper) {
		return executeQuery(query, rowMapper, preparedStatement -> {
		});
	}

	public Long executeUpdate(String query, PreparedStatementSetter preparedStatementSetter) {
		try (Connection connection = connectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
		) {
			preparedStatementSetter.setArgument(preparedStatement);
			preparedStatement.executeUpdate();
			return generatedKey(preparedStatement);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	private Long generatedKey(final PreparedStatement preparedStatement) {
		try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
			if (resultSet.next()) {
				return resultSet.getLong(1);
			}
			return null;
		} catch (SQLException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public Long executeUpdate(String query) {
		return executeUpdate(query, preparedStatement -> {
		});
	}

}
