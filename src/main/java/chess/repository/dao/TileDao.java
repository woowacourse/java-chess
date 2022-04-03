package chess.repository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TileDao {

	private static final String POSITION = "position";
	private static final String PIECE = "piece";

	private final ConnectionManager connectionManager = new ConnectionManager();

	public void insertAll(Map<String, String> tiles, int foreignKey) {
		Connection connection = connectionManager.getConnection();
		for (String position : tiles.keySet()) {
			insert(connection, position, tiles.get(position), foreignKey);
		}
	}

	private void insert(Connection connection, String position, String piece, int foreignKey) {
		String sql = "insert into tile (position, piece, game_id) values (? ,?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, position);
			statement.setString(2, piece);
			statement.setInt(3, foreignKey);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public Map<String, String> selectByGameId(int foreignKey) {
		Connection connection = connectionManager.getConnection();
		String sql = "select position, piece from tile where game_id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, foreignKey);

			ResultSet result = statement.executeQuery();
			return makeResult(result);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public Map<String, String> selectByGameName(String gameName) {
		Connection connection = connectionManager.getConnection();
		String sql = "select t.position, t.piece from tile t natural join game g where g.name = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, gameName);

			return makeResult(statement.executeQuery());
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private Map<String, String> makeResult(ResultSet result) throws SQLException {
		Map<String, String> tiles = new HashMap<>();
		while (result.next()) {
			tiles.put(result.getString(POSITION), result.getString(PIECE));
		}
		return tiles;
	}

	public void deleteByPosition(String position, String gameName) {
		Connection connection = connectionManager.getConnection();
		String sql = "delete from tile where position = ? and "
			+ "game_id = (select game_id from game where name = ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, position);
			statement.setString(2, gameName);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}
}

