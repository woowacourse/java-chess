package chess.domain.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import chess.domain.dto.ChessGameDto;

public class RoomDao extends MySqlDao {
	public List<String> findAll() throws SQLException {
		String query = "SELECT * FROM room";
		PreparedStatement pstmt = connect().prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();

		List<String> roomNames = new ArrayList<>();
		while (rs.next()) {
			roomNames.add(rs.getString("room_name"));
		}
		return new ArrayList<>(roomNames);
	}

	public Optional<String> findByRoomName(String roomName, String columnLabel) throws SQLException {
		String query = "SELECT * FROM room WHERE room_name = ?";
		PreparedStatement pstmt = connect().prepareStatement(query);
		pstmt.setString(1, roomName);
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next()) {
			return Optional.empty();
		}
		return Optional.of(rs.getString(columnLabel));
	}

	public int addRoom(ChessGameDto chessGameDto) throws SQLException {
		String query = "INSERT INTO room(room_name, board, turn, finish_flag) VALUES (?, ?, ?, ?)";
		PreparedStatement pstmt = connect().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, chessGameDto.getRoomName());
		pstmt.setString(2, chessGameDto.getBoard());
		pstmt.setString(3, chessGameDto.getTurn());
		pstmt.setString(4, chessGameDto.getFinishFlag());
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

	public void updateRoom(ChessGameDto chessGameDto) throws SQLException {
		String query = "UPDATE room SET board = ?, turn = ?, finish_flag = ? WHERE room_name = ?";
		PreparedStatement pstmt = connect().prepareStatement(query);
		pstmt.setString(1, chessGameDto.getBoard());
		pstmt.setString(2, chessGameDto.getTurn());
		pstmt.setString(3, chessGameDto.getFinishFlag());
		pstmt.setString(4, chessGameDto.getRoomName());
		pstmt.executeUpdate();
	}
}
