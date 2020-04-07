package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.gamestate.GameState;

public class GameStateDAO {
	public void addGameState(Long roomID, GameState gameState) throws SQLException {
		String query = "INSERT INTO gamestate(room_id, message) VALUES (?, ?)";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		pstmt.setLong(1, roomID);
		pstmt.setString(2, gameState.toString());
		System.out.println("test");
		System.out.println(pstmt.toString());
		pstmt.executeUpdate();
	}

	public void delete(Long roomID) throws SQLException {
		String query = "DELETE FROM gamestate WHERE room_id = ?";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		pstmt.setString(1, String.valueOf(roomID));
		pstmt.executeUpdate();
	}

	public void deleteGameState(Long roomID, GameState gameState) throws SQLException {
		String query = "DELETE FROM gamestate WHERE message = ?";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		pstmt.setString(1, String.valueOf(roomID));
		pstmt.executeUpdate();
	}

	public GameState findGameState(Long roomID) throws SQLException {
		String query = "SELECT * FROM gamestate WHERE room_id = ?";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		pstmt.setString(1, String.valueOf(roomID));
		ResultSet resultSet = pstmt.executeQuery();

		if (!resultSet.next())
			return null;

		return GameState.of(resultSet.getString("message"));
	}

	public void updateMessage(Long roomID, GameState gameState, GameState opposingTeam) throws SQLException {
		String query = "UPDATE gamestate SET message = ? WHERE room_id = ? AND message = ?";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		pstmt.setString(1, opposingTeam.toString());
		pstmt.setLong(2, roomID);
		pstmt.setString(3, gameState.toString());

		pstmt.executeUpdate();
	}
}