package chess.domain.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDao extends MySqlDao {
	public String findByRoomId(String roomId) throws SQLException {
		String query = "SELECT * FROM room WHERE room_id = ?";
		PreparedStatement pstmt = connect().prepareStatement(query);
		pstmt.setString(1, roomId);
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next())
			return null;
		return rs.getString("board");
	}

	public void addRoom(String roomName, String board, String turn, String finishFlag) throws SQLException {
		String query = "INSERT INTO room(room_name, board, turn, finish_flag) VALUES (?, ?, ?, ?)";
		PreparedStatement pstmt = connect().prepareStatement(query);
		pstmt.setString(1, roomName);
		pstmt.setString(2, board);
		pstmt.setString(3, turn);
		pstmt.setString(4, finishFlag);
		pstmt.executeUpdate();
	}

	public void deleteRoom(String roomName) throws SQLException {
		String query = "DELETE FROM room WHERE room_name = ?";
		PreparedStatement pstmt = connect().prepareStatement(query);
		pstmt.setString(1, roomName);
		pstmt.executeUpdate();
	}

	public void updateBoard(String roomName, String board) throws SQLException {
		String query = "UPDATE room SET board = ? WHERE room_name = ?";
		PreparedStatement pstmt = connect().prepareStatement(query);
		pstmt.setString(1, board);
		pstmt.setString(2, roomName);
		pstmt.executeUpdate();
	}
}
