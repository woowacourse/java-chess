package chess.repository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChessGameDao {

	private final ConnectionManager connectionManager = new ConnectionManager();

	public int insert(String name, String state) {
		Connection connection = connectionManager.getConnection();
		int key = -1;
		String sql = "insert into game (name, state) values (? ,?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, name);
			statement.setString(2, state);
			statement.executeUpdate();

			ResultSet result = statement.getGeneratedKeys();
			if (result.next()) {
				key = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return key;
	}

	public void delete(String name) {
		Connection connection = connectionManager.getConnection();
		String sql = "delete from game where name = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
