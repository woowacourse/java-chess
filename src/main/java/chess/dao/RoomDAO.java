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

		String query = "SELECT room_id, room_name, room_color FROM room WHERE room_id = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, roomId);
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next()) {
			return null;
		}

		Room room = new Room(
			rs.getInt("room_id"),
			rs.getString("room_name"),
			rs.getString("room_color")
		);

		JDBCConnector.closeConnection(con);

		return room;
	}

	public void addRoom(String roomName, String roomColor) throws SQLException {
		Connection con = JDBCConnector.getConnection();

		String query = "INSERT INTO room(room_name, room_color) VALUES (?, ?)";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, roomName);
		pstmt.setString(2, roomColor);
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

		int roomId = rs.getInt("room_id");

		JDBCConnector.closeConnection(con);

		return roomId;
	}

	public List<Room> findAllRoom() throws SQLException {
		Connection con = JDBCConnector.getConnection();

		List<Room> rooms = new ArrayList<>();

		String query = "SELECT room_id, room_name, room_color FROM room";
		PreparedStatement pstmt = con.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int roomId = rs.getInt("room_id");
			String roomName = rs.getString("room_name");
			String roomColor = rs.getString("room_color");

			rooms.add(new Room(roomId, roomName, roomColor));
		}

		JDBCConnector.closeConnection(con);

		return rooms;
	}

	public void updateRoomColorById(int roomId, Color roomColor) throws SQLException {
		Connection con = JDBCConnector.getConnection();

		String query = "UPDATE room SET room_color = ? WHERE room_id = ?";

		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, roomColor.name());
		pstmt.setInt(2, roomId);
		pstmt.executeUpdate();

		JDBCConnector.closeConnection(con);
	}

	public Color findRoomColorById(int roomId) throws SQLException {
		Connection con = JDBCConnector.getConnection();

		String query = "SELECT room_color FROM room WHERE room_id = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, roomId);
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next()) {
			return null;
		}

		Color color = Color.valueOf(rs.getString("room_color"));

		JDBCConnector.closeConnection(con);

		return color;
	}
}
