package chess.dao;

import chess.domain.Turn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDao {
	public int findTurnPlayerId(int roomId) throws SQLException, ClassNotFoundException {
		String query = "select turn from room where room_id = ?";
		try (Connection con = ConnectionLoader.load();
			 PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setInt(1, roomId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (!rs.next()) {
					throw new IllegalArgumentException("Turn이 잘못되었습니다.");
				}
				return rs.getInt("turn");
			}
		}
	}

	public int create(int playerId) throws SQLException, ClassNotFoundException {
		String query = "insert into room(turn, player1_id, player2_id) value (?, ?, null)";
		try (Connection con = ConnectionLoader.load(); PreparedStatement pstmt = con.prepareStatement(query,
            PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setInt(1, playerId);
			pstmt.setInt(2, playerId);
			pstmt.executeUpdate();
			return getId(pstmt);
		}

	}

	private int getId(PreparedStatement pstmt) throws SQLException {
		try (ResultSet rs = pstmt.getGeneratedKeys()) {
			if (rs.next()) {
				return rs.getInt(1);
			}
			throw new IllegalArgumentException();
		}
	}
}
