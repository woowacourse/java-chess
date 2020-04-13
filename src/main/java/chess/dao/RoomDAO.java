package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.Color;
import chess.domain.room.Room;

public class RoomDAO {
	private static final RoomDAO ROOM_DAO = new RoomDAO();

	public static RoomDAO getInstance() {
		return ROOM_DAO;
	}

	public void addRoom(String roomName, String roomColor) throws SQLException {
		String query = "INSERT INTO room(room_name, room_color) VALUES (?, ?)";

		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setParameters(final PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, roomName);
				pstmt.setString(2, roomColor);
			}
		};

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.executeUpdate(query, pss);
	}

	public void removeRoomById(int roomId) throws SQLException {
		String query = "DELETE FROM room WHERE room_id = ?";

		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setParameters(final PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, roomId);
			}
		};

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.executeUpdate(query, pss);
	}

	public void updateRoomColorById(int roomId, Color roomColor) throws SQLException {
		String query = "UPDATE room SET room_color = ? WHERE room_id = ?";

		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setParameters(final PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, roomColor.name());
				pstmt.setInt(2, roomId);
			}
		};

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.executeUpdate(query, pss);
	}

	public Room findRoomById(int roomId) throws SQLException {
		String query = "SELECT room_id, room_name, room_color FROM room WHERE room_id = ?";

		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setParameters(final PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, roomId);
			}
		};

		RowMapper rm = new RowMapper() {
			@Override
			public Object mapRow(final ResultSet rs) throws SQLException {
				if (!rs.next()) {
					return null;
				}
				return new Room(
					rs.getInt("room_id"),
					rs.getString("room_name"),
					rs.getString("room_color")
				);
			}
		};
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		return (Room)jdbcTemplate.executeQuery(query, pss, rm);
	}

	public int findRoomIdByRoomName(String roomName) throws SQLException {
		String query = "SELECT room_id FROM room WHERE room_name = ?";

		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setParameters(final PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, roomName);
			}
		};

		RowMapper rm = new RowMapper() {
			@Override
			public Object mapRow(final ResultSet rs) throws SQLException {
				if (!rs.next()) {
					return 0;
				}

				return rs.getInt("room_id");
			}
		};
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		return (int)jdbcTemplate.executeQuery(query, pss, rm);
	}

	public List<Room> findAllRoom() throws SQLException {
		String query = "SELECT room_id, room_name, room_color FROM room";

		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setParameters(final PreparedStatement pstmt) throws SQLException {

			}
		};

		RowMapper rm = new RowMapper() {
			@Override
			public Object mapRow(final ResultSet rs) throws SQLException {
				List<Room> rooms = new ArrayList<>();

				while (rs.next()) {
					int roomId = rs.getInt("room_id");
					String roomName = rs.getString("room_name");
					String roomColor = rs.getString("room_color");

					rooms.add(new Room(roomId, roomName, roomColor));
				}
				return rooms;
			}
		};
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		return (List<Room>)jdbcTemplate.executeQuery(query, pss, rm);
	}

	public Color findRoomColorById(int roomId) throws SQLException {
		String query = "SELECT room_color FROM room WHERE room_id = ?";

		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setParameters(final PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, roomId);
			}
		};

		RowMapper rm = new RowMapper() {
			@Override
			public Object mapRow(final ResultSet rs) throws SQLException {
				if (!rs.next()) {
					return null;
				}
				return Color.valueOf(rs.getString("room_color"));
			}
		};

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		return (Color)jdbcTemplate.executeQuery(query, pss, rm);
	}
}
