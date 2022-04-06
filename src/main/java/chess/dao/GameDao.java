package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameDao {

	private final Connection connection;

	public GameDao(final Connection connection) {
		this.connection = connection;
	}

	public GameDao() {
		this(DatabaseConnection.getConnection());
	}

	public void save(final String state) {
		final String sql = "insert into game (command_log) values (?)";
		try {
			final PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, state);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
