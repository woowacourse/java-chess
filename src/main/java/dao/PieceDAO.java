package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.board.Location;
import chess.piece.Piece;
import chess.team.Team;

public class PieceDAO {
	public void addPiece(Long roomID, Location location, Piece piece) throws SQLException {
		String query = "INSERT INTO piece(room_id, location, name, team) VALUES (?, ?, ?, ?)";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		pstmt.setLong(1, roomID);
		pstmt.setString(2, location.toString());
		pstmt.setString(3, String.valueOf(piece.getName()));
		pstmt.setString(4, piece.getTeam().getName());
		pstmt.executeUpdate();
	}

	public void deletePiece(Long roomID, Location location) throws SQLException {
		String query = "DELETE FROM piece WHERE room_id = ? AND location = ?";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		pstmt.setLong(1, roomID);
		pstmt.setString(2, location.toString());
		System.out.println(pstmt.toString());
		pstmt.executeUpdate();
	}

	public Piece findPiece(Long roomID, Location location) throws SQLException {
		String query = "SELECT * FROM piece WHERE room_id = ? AND location = ?";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		pstmt.setLong(1, roomID);
		pstmt.setString(2, location.toString());
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next()) {
			return null;
		}

		String name = rs.getString("name");
		Team team = Team.of(rs.getString("team"));

		return Piece.createOnePiece(name, team);
	}

	public Map<Location, Piece> findAll(Long roomID) throws SQLException {
		String query = "SELECT * FROM piece WHERE room_id = ?";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		pstmt.setLong(1, roomID);
		ResultSet rs = pstmt.executeQuery();

		Map<Location, Piece> result = new HashMap<>();

		while (rs.next()) {
			String name = rs.getString("name");
			Team team = Team.of(rs.getString("team"));
			Location location = Location.of(rs.getString("location"));

			result.put(location, Piece.createOnePiece(name, team));
		}

		return result;
	}

	public void updateLocation(Long roomID, Location now, Location destination) throws SQLException {
		String query = "UPDATE piece SET location = ? WHERE room_id = ? AND location = ?";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		pstmt.setString(1, destination.toString());
		pstmt.setLong(2, roomID);
		pstmt.setString(3, now.toString());
		System.out.println(pstmt.toString());
		pstmt.executeUpdate();
	}

	public void deleteAll(Long roomID) throws SQLException {
		String query = "DELETE FROM	piece WHERE room_id	= ?";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		pstmt.setLong(1, roomID);
		pstmt.executeUpdate();
	}
}
