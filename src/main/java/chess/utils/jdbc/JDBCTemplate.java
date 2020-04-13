package chess.utils.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import chess.exception.SQLAccessException;

public class JDBCTemplate {
	private final DataSource dataSource;

	public JDBCTemplate(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public <T> Optional<T> executeQuery(String query, RowMapper<T> mapper, PrepareStatementSetter setter) {
		try (Connection con = dataSource.getConnection();
			 PreparedStatement preparedStatement = con.prepareStatement(query)) {
			setter.set(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return Optional.empty();
			}
			return Optional.of(mapper.map(resultSet));
		} catch (SQLException e) {
			throw new SQLAccessException();
		}
	}

	public int executeUpdate(String query, PrepareStatementSetter setter) {
		try (Connection con = dataSource.getConnection();
			 PreparedStatement preparedStatement = con.prepareStatement(query)) {
			setter.set(preparedStatement);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLAccessException();
		}
	}
}
