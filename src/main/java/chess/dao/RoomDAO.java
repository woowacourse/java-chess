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

	public void addRoom(String roomName, String roomColor) throws SQLException {
		String query = "INSERT INTO room(room_name, room_color) VALUES (?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate() {
			@Override
			public void setParameters(final PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, roomName);
				pstmt.setString(2, roomColor);
			}
		};

		jdbcTemplate.executeUpdate(query);
	}

	public void removeRoomById(int roomId) throws SQLException {
		String query = "DELETE FROM room WHERE room_id = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate() {
			@Override
			public void setParameters(final PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, roomId);
			}
		};

		jdbcTemplate.executeUpdate(query);
	}

	public void updateRoomColorById(int roomId, Color roomColor) throws SQLException {
		String query = "UPDATE room SET room_color = ? WHERE room_id = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate() {
			@Override
			public void setParameters(final PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, roomColor.name());
				pstmt.setInt(2, roomId);
			}
		};

		jdbcTemplate.executeUpdate(query);
	}

	public Room findRoomById(int roomId) throws SQLException {
		String query = "SELECT room_id, room_name, room_color FROM room WHERE room_id = ?";

		try (Connection con = JDBCConnector.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query)
		) {
			pstmt.setInt(1, roomId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (!rs.next()) {
					return null;
				}
				return new Room(
					rs.getInt("room_id"),
					rs.getString("room_name"),
					rs.getString("room_color")
				);
			}
		}
	}

	public int findRoomIdByRoomName(String roomName) throws SQLException {
		String query = "SELECT room_id FROM room WHERE room_name = ?";

		try (Connection con = JDBCConnector.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query)
		) {
			pstmt.setString(1, roomName);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (!rs.next()) {
					return 0;
				}

				return rs.getInt("room_id");
			}
		}
	}

	public List<Room> findAllRoom() throws SQLException {
		String query = "SELECT room_id, room_name, room_color FROM room";
		List<Room> rooms = new ArrayList<>();

		try (Connection con = JDBCConnector.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query);
		) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					int roomId = rs.getInt("room_id");
					String roomName = rs.getString("room_name");
					String roomColor = rs.getString("room_color");

					rooms.add(new Room(roomId, roomName, roomColor));
				}
				return rooms;
			}
		}
	}

	public Color findRoomColorById(int roomId) throws SQLException {
		String query = "SELECT room_color FROM room WHERE room_id = ?";

		try (Connection con = JDBCConnector.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query)
		) {
			pstmt.setInt(1, roomId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (!rs.next()) {
					return null;
				}
				return Color.valueOf(rs.getString("room_color"));
			}
		}
	}
}
