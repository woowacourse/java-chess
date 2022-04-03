package chess.repository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameDao {

	private static final String DUPLICATE_GAME_NAME = "해당 이름의 게임이 이미 존재합니다.";
	private static final String NOT_FOUND_PRIMARY_KEY = "기본키를 찾을 수 없습니다.";

	private static final String GAME_ID = "game_id";
	private static final String STATE = "state";
	private static final String NAME = "name";

	private final ConnectionManager connectionManager = new ConnectionManager();

	public int insert(String name, String state) {
		Connection connection = connectionManager.getConnection();
		String sql = "insert into game (name, state) values (? ,?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, name);
			statement.setString(2, state);
			statement.executeUpdate();

			ResultSet result = statement.getGeneratedKeys();
			return getKey(result);
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new IllegalStateException(DUPLICATE_GAME_NAME);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private int getKey(ResultSet result) throws SQLException {
		if (result.next()) {
			return result.getInt(1);
		}
		throw new IllegalArgumentException(NOT_FOUND_PRIMARY_KEY);
	}

	public Map<String, String> selectByName(String name) {
		Connection connection = connectionManager.getConnection();
		String sql = "select game_id, state from game where name = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);

			ResultSet result = statement.executeQuery();
			return makeResult(result);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private Map<String, String> makeResult(ResultSet result) throws SQLException {
		Map<String, String> game = new HashMap<>();
		if (result.next()) {
			game.put(GAME_ID, String.valueOf(result.getInt(GAME_ID)));
			game.put(STATE, result.getString(STATE));
		}
		return game;
	}

	public void delete(String name) {
		Connection connection = connectionManager.getConnection();
		String sql = "delete from game where name = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public List<String> findAllNames() {
		Connection connection = connectionManager.getConnection();
		String sql = "select name from game";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			return makeNames(statement.executeQuery());
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private List<String> makeNames(ResultSet result) throws SQLException {
		List<String> names = new ArrayList<>();
		while (result.next()) {
			names.add(result.getString(NAME));
		}
		return names;
	}
}
