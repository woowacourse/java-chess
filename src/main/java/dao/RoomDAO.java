package dao;

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
		PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();

		List<String> rooms = new ArrayList<>();

		while (rs.next()) {
			String id = rs.getString("id");
			rooms.add(id);
		}
		Map<String, Object> result = new HashMap<>();

		result.put("rooms", rooms);
		return result;
	}

	public String createRoom() throws SQLException {
		String query = "INSERT INTO ROOM value ()";
		PreparedStatement pstmt = ConnectionManager.getConnection()
			.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		pstmt.executeUpdate();

		try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				return String.valueOf(generatedKeys.getLong(1));
			} else {
				throw new SQLException("방을 생성하지 못했습니다.");
			}
		}
	}

	public void delete(String roomID) throws SQLException {
		String query = "DELETE FROM room WHERE id = ?";
		PreparedStatement pstmt = ConnectionManager.getConnection()
			.prepareStatement(query);

		pstmt.setString(1, roomID);
		pstmt.executeUpdate();
	}
}
