package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.Color;
import chess.domain.room.Room;
import chess.util.JDBCConnector;

public class RoomDAO {
	private static final RoomDAO ROOM_DAO = new RoomDAO();

	public static RoomDAO getInstance() {
		return ROOM_DAO;
	}

	public Room findRoomById(int roomId) throws SQLException {
		Connection con = JDBCConnector.getConnection();

		String query = "SELECT room_id, room_name, turn FROM room WHERE room_id = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, roomId);
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next()) {
			return null;
		}

		Room room = new Room(
			rs.getInt("room_id"),
			rs.getString("room_name"),
			rs.getString("turn")
		);

		JDBCConnector.closeConnection(con);

		return room;
	}

	public void addRoom(String room_name, String turn) throws SQLException {
		Connection con = JDBCConnector.getConnection();

		String query = "INSERT INTO room(room_name, turn) VALUES (?, ?)";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, room_name);
		pstmt.setString(2, turn);
		pstmt.executeUpdate();

		JDBCConnector.closeConnection(con);
	}

	public void removeRoomById(int roomId) throws SQLException {
		Connection con = JDBCConnector.getConnection();


		String query = "DELETE FROM room WHERE room_id = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, roomId);
		pstmt.executeUpdate();

		JDBCConnector.closeConnection(con);
	}

	public int findRoomIdByRoomName(String roomName) throws SQLException {
		Connection con = JDBCConnector.getConnection();

		String query = "SELECT room_id FROM room WHERE room_name = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, roomName);
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next()) {
			return 0;
		}
		JDBCConnector.closeConnection(con);

		return rs.getInt("room_id");
	}

	public List<Room> findAllRoom() throws SQLException {
		Connection con = JDBCConnector.getConnection();

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

		JDBCConnector.closeConnection(con);

		return rooms;
	}

	public void updateTurnById(int roomId, Color color) throws SQLException {
		Connection con = JDBCConnector.getConnection();

		String query = "UPDATE room SET turn = ? WHERE room_id = ?";

		PreparedStatement pstmt = con.prepareStatement(query);
		// TODO : Color를 모두 UpperCase로 바꾸자
		pstmt.setString(1, color.name().toLowerCase());
		pstmt.setInt(2, roomId);
		pstmt.executeUpdate();

		JDBCConnector.closeConnection(con);
	}

	public String findTurnById(int roomId) throws SQLException {
		Connection con = JDBCConnector.getConnection();

		String query = "SELECT turn FROM room WHERE room_id = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, roomId);
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next()) {
			return null;
		}

		JDBCConnector.closeConnection(con);

		return rs.getString("turn");
	}
}
