package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomDAO {
	public Map<String, Object> findAll() throws SQLException {
		String query = "SELECT * FROM room";
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();

		List<String> rooms = new ArrayList<>();

		while (rs.next()) {
			String id = rs.getString("id");
			rooms.add(id);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("rooms", rooms);

		ConnectionManager.closeConnection(connection);
		return result;
	}

	public String createRoom() throws SQLException {
		String query = "INSERT INTO room value ()";
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		pstmt.executeUpdate();

		try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				return String.valueOf(generatedKeys.getLong(1));
			} else {
				throw new SQLException("방을 생성하지 못했습니다.");
			}
		} finally {
			ConnectionManager.closeConnection(connection);
		}
	}

	public void delete(String roomID) throws SQLException {
		String query = "DELETE FROM room WHERE id = ?";
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(query);

		pstmt.setString(1, roomID);
		pstmt.executeUpdate();

		ConnectionManager.closeConnection(connection);
	}
}
