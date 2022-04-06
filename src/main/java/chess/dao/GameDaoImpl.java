package chess.dao;

import chess.domain.game.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GameDaoImpl implements GameDao {

	private static final String SAVE_ERROR = "해당 값을 저장할 수 없습니다.";
	private static final String NAME_DUPLICATION_ERROR = "게임 이름은 중복될 수 없습니다.";
	private static final String NOT_FOUND_ITEM_ERROR = "해당 키를 가진 데이터가 없습니다.";

	private final Connection connection;

	public GameDaoImpl(final Connection connection) {
		this.connection = connection;
	}

	public GameDaoImpl() {
		this(DatabaseConnection.getConnection());
	}

	@Override
	public int save(final Game game) {
		final String sql = "insert into game (name, command_log) values (?, ?)";
		try (final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, game.getName());
			statement.setString(2, game.getCommandLog());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException(NAME_DUPLICATION_ERROR);
		}
		throw new NoSuchElementException(SAVE_ERROR);
	}

	@Override
	public void update(final int gameId, final String state) {
		final String sql = "update game set command_log = concat(command_log, '\n', ?) where game.id = ?";
		try (final PreparedStatement statement = connection.prepareStatement(sql)) {
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
		try (final PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, gameId);
			final ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next()) {
				throw new NoSuchElementException(NOT_FOUND_ITEM_ERROR);
			}
			return new Game(
					resultSet.getInt("id"),
					resultSet.getString("name"),
					resultSet.getString("command_log")
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new NoSuchElementException(NOT_FOUND_ITEM_ERROR);
	}

	@Override
	public List<Game> findAll() {
		final String sql = "select id, name, command_log from game";
		final List<Game> games = new ArrayList<>();
		try (final PreparedStatement statement = connection.prepareStatement(sql)) {
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

	@Override
	public void delete(final int gameId) {
		final String sql = "delete from game where game.id = ?";
		try (final PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, gameId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
