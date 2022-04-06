package chess.dao;

import chess.domain.game.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GameDaoImpl implements GameDao {

	private final Connection connection;

	public GameDaoImpl(final Connection connection) {
		this.connection = connection;
	}

	public GameDaoImpl() {
		this(DatabaseConnection.getConnection());
	}

	@Override
	public int save(final String name, final String state) {
		final String sql = "insert into game (name, command_log) values (?, ?)";
		int insertId = -1;
		try {
			final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, name);
			statement.setString(2, state);
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

	@Override
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

	@Override
	public Game findById(final int gameId) {
		final String sql = "select id, name, command_log from game where id = ?";
		try {
			final PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, gameId);
			final ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			return new Game(
					resultSet.getInt("id"),
					resultSet.getString("name"),
					resultSet.getString("command_log")
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Game> findAll() {
		final String sql = "select id, name, command_log from game";
		final List<Game> games = new ArrayList<>();
		try {
			final PreparedStatement statement = connection.prepareStatement(sql);
			final ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Game game = new Game(
						resultSet.getInt("id"),
						resultSet.getString("name"),
						resultSet.getString("command_log")
				);
				games.add(game);
			}
			return games;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return games;
	}
}
