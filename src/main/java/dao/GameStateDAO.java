package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.gamestate.GameState;

public class GameStateDAO {
	public void addGameState(GameState gameState) throws SQLException {
		String query = "INSERT INTO gamestate VALUES (?)";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		pstmt.setString(1, gameState.toString());
		pstmt.executeUpdate();
	}

	public void deleteAll() throws SQLException {
		String query = "DELETE FROM gamestate";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		pstmt.executeUpdate();
	}

	public void deleteGameState(GameState gameState) throws SQLException {
		String query = "DELETE FROM gamestate WHERE message = ?";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		pstmt.setString(1, gameState.toString());
		pstmt.executeUpdate();
	}

	public GameState findGameState() throws SQLException {
		String query = "SELECT * FROM gamestate";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);

		ResultSet resultSet = pstmt.executeQuery();

		if (!resultSet.next())
			return null;

		return GameState.of(resultSet.getString("message"));
	}

	public void updateMessage(GameState gameState, GameState opposingTeam) throws SQLException {
		String query = "UPDATE gamestate SET message = ? WHERE message = ?";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);

		pstmt.setString(1, opposingTeam.toString());
		pstmt.setString(2, gameState.toString());

		pstmt.executeUpdate();
	}
}