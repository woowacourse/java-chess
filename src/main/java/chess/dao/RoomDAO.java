package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.room.Room;
import chess.util.JDBCConnector;

public class RoomDAO {
	private static final RoomDAO ROOM_DAO = new RoomDAO();
	Connection con = JDBCConnector.getConnection(); // TODO : 이렇게 해줘도 상관없나?

	public static RoomDAO getInstance() {
		return ROOM_DAO;
	}

	public Room findRoomById(int roomId) throws SQLException {
		String query = "SELECT room_id, room_name, turn FROM room WHERE room_id = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, roomId);
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next()) {
			return null;
		}

		return new Room(
			rs.getInt("room_id"),
			rs.getString("room_name"),
			rs.getString("turn")
		);
	}

	public void addRoom(String room_name, String turn) throws SQLException {
		String query = "INSERT INTO room(room_name, turn) VALUES (?, ?)";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, room_name);
		pstmt.setString(2, turn);
		pstmt.executeUpdate();
	}

	public void removeRoomById(int roomId) throws SQLException {
		String query = "DELETE FROM room WHERE room_id = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, roomId);
		pstmt.executeUpdate();
	}

	public int findRoomIdByRoomName(String roomName) throws SQLException {
		String query = "SELECT room_id FROM room WHERE room_name = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, roomName);
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next()) {
			return 0;
		}

		return rs.getInt("room_id");
	}

	public List<Room> findAllRoom() throws SQLException {
		List<Room> rooms = new ArrayList<>();

		String query = "SELECT room_id, room_name, turn FROM room";
		PreparedStatement pstmt = con.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int roomId = rs.getInt("room_id");
			String roomName = rs.getString("room_name");
			String turn = rs.getString("turn");

			rooms.add(new Room(roomId, roomName, turn));
		}

		return rooms;
	}
}
