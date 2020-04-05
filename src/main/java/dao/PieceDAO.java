package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.board.Location;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.team.Team;

public class PieceDAO {
	private static Piece createOnePiece(String name, Team team) {
		Map<String, Piece> map = new HashMap<>();
		map.put("p", Pawn.of(team));
		map.put("k", King.of(team));
		map.put("r", Rook.of(team));
		map.put("q", Queen.of(team));
		map.put("n", Knight.of(team));
		map.put("b", Bishop.of(team));
		return map.get(name.toLowerCase());
	}

	public void addPiece(Location location, Piece piece) throws SQLException {
		String query = "INSERT INTO piece VALUES (?, ?, ?)";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		pstmt.setString(1, location.toString());
		pstmt.setString(2, String.valueOf(piece.getName()));
		pstmt.setString(3, piece.getTeam().getName());
		pstmt.executeUpdate();
	}

	public void deletePiece(Location location) throws SQLException {
		String query = "DELETE FROM piece WHERE location = ?";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		pstmt.setString(1, location.toString());
		pstmt.executeUpdate();
	}

	public Piece findPiece(Location location) throws SQLException {
		String query = "SELECT * FROM piece WHERE location = ?";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		pstmt.setString(1, location.toString());
		ResultSet rs = pstmt.executeQuery();

		String name = rs.getString("name");
		Team team = Team.of(rs.getString("team"));

		return createOnePiece(name, team);
	}

	// todo : 테스트
	public Map<Location, Piece> findAll() throws SQLException {
		String query = "SELECT * FROM piece";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();

		Map<Location, Piece> result = new HashMap<>();

		while (rs.next()) {
			String name = rs.getString("name");
			Team team = Team.of(rs.getString("team"));
			Location location = Location.of(rs.getString("location"));

			result.put(location, createOnePiece(name, team));
		}

		return result;
	}

	// todo : 테스트
	public void updateLocation(Location now, Location destination) throws SQLException {
		String query = "UPDATE piece SET location = ? WHERE location = ?";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		pstmt.setString(1, destination.toString());
		pstmt.setString(2, now.toString());
		pstmt.executeUpdate();
	}

	public void deleteAll() throws SQLException {
		String query = "DELETE FROM	piece;";
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		pstmt.executeUpdate();
	}
}
