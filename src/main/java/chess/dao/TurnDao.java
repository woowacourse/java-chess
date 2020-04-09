package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.domain.piece.Color;

public class TurnDao {
	public Color findTurn() {
		try (Connection connection = new SQLConnector().getConnection()) {
			String query = "SELECT * FROM state WHERE id = 1";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			if (!result.next()) {
				return null;
			}

			String nowTurn = result.getString("turn");
			return Color.of(nowTurn);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void editTurn(Color turn) {
		try (Connection connection = new SQLConnector().getConnection()) {
			String query = "INSERT INTO state VALUE (1, ?) ON DUPLICATE KEY UPDATE turn = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, turn.name());
			statement.setString(2, turn.name());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
