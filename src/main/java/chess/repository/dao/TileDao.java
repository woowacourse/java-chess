package chess.repository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class TileDao {

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
			e.printStackTrace();
		}
	}

	public void deleteAll() {
		Connection connection = connectionManager.getConnection();
		String sql = "delete from tile";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
