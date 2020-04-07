package chess.domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.domain.piece.Color;

public class TurnDao {
	public Color findTurn() throws SQLException {
		Connection connection = new SQLConnector().getConnection();
		String query = "SELECT * FROM state WHERE id = 1";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet result = statement.executeQuery();

		if (!result.next()) {
			return null;
		}

		String nowTurn = result.getString("turn");

		connection.close();
		return Color.of(nowTurn);
	}

	public void editTurn(Color turn) throws SQLException {
		Connection connection = new SQLConnector().getConnection();
		String query = "INSERT INTO state VALUE (1, ?) ON DUPLICATE KEY UPDATE turn = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, turn.name());
		statement.setString(2, turn.name());
		statement.executeUpdate();
		connection.close();
	}
}
