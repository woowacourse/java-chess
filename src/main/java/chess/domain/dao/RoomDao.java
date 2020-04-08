package chess.domain.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class RoomDao extends MySqlDao {
	public Optional<String> findByRoomId(String roomId) throws SQLException {
		String query = "SELECT * FROM room WHERE room_id = ?";
		return find(query, roomId);
	}

	public Optional<String> findByRoomName(String roomName) throws SQLException {
		String query = "SELECT * FROM room WHERE room_name = ?";
		return find(query, roomName);
	}

	private Optional<String> find(String query, String roomInfo) throws SQLException {
		PreparedStatement pstmt = connect().prepareStatement(query);
		pstmt.setString(1, roomInfo);
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next()) {
			return Optional.empty();
		}
		String board = rs.getString("board");
		return Optional.of(board);
	}

	public int addRoom(String roomName, String board, String turn, String finishFlag) throws SQLException {
		String query = "INSERT INTO room(room_name, board, turn, finish_flag) VALUES (?, ?, ?, ?)";
		PreparedStatement pstmt = connect().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, roomName);
		pstmt.setString(2, board);
		pstmt.setString(3, turn);
		pstmt.setString(4, finishFlag);
		pstmt.executeUpdate();

		ResultSet rs = pstmt.getGeneratedKeys();
		if (!rs.next()) {
			throw new IllegalArgumentException("DB에 방을 추가할 수 없습니다.");
		}
		return rs.getInt("GENERATED_KEY");
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
