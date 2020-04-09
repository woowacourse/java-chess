package chess.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {
	DataSource dataSource;

	public JdbcTemplate(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public <T> T query(String query, RowMapper<T> rowMapper, PreparedStatementSetter preparedStatementSetter) {
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatementSetter.setArgument(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			return rowMapper.mapRow(resultSet);
		} catch (SQLException exception) {
			System.err.println(exception.getMessage());
		}
		return null;
	}

	public <T> T query(String query, RowMapper<T> rowMapper) {
		return query(query, rowMapper, (preparedStatement) -> {
		});
	}

	public void update(String query, PreparedStatementSetter preparedStatementSetter) {
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatementSetter.setArgument(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			System.err.println(exception.getMessage());
		}
	}

	public void update(String query) {
		update(query, (preparedStatement) -> {
		});
	}
}
