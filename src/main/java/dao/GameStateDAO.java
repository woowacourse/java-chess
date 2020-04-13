package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.gamestate.GameState;

public class GameStateDAO {
	public void addGameState(Long roomID, GameState gameState) throws SQLException {
		String query = "INSERT INTO gamestate(room_id, message) VALUES (?, ?)";
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(query);

		pstmt.setLong(1, roomID);
		pstmt.setString(2, gameState.toString());
		pstmt.executeUpdate();

		ConnectionManager.closeConnection(connection);
	}

	public GameState findGameState(Long roomID) throws SQLException {
		String query = "SELECT * FROM gamestate WHERE room_id = ?";
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, String.valueOf(roomID));
		ResultSet resultSet = pstmt.executeQuery();

		if (!resultSet.next()) {
			ConnectionManager.closeConnection(connection);
			return null;
		}

		String message = resultSet.getString("message");
		ConnectionManager.closeConnection(connection);
		return GameState.of(message);
	}

	public void updateMessage(Long roomID, GameState gameState, GameState opposingTeam) throws SQLException {
		String query = "UPDATE gamestate SET message = ? WHERE room_id = ? AND message = ?";
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, opposingTeam.toString());
		pstmt.setLong(2, roomID);
		pstmt.setString(3, gameState.toString());

		pstmt.executeUpdate();
		ConnectionManager.closeConnection(connection);
	}
}