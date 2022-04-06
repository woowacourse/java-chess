package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDao {

	private final Connection connection;

	public GameDao(final Connection connection) {
		this.connection = connection;
	}

	public GameDao() {
		this(DatabaseConnection.getConnection());
	}

	public int save(final String state) {
		final String sql = "insert into game (command_log) values (?)";
		int insertId = -1;
		try {
			final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, state);
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				insertId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertId;
	}

	public void update(final int gameId, final String state) {
		final String sql = "update game set command_log = concat(command_log, '\n', ?) where game.id = ?";
		try {
			final PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, state);
			statement.setInt(2, gameId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
