package chess.domain.web;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class LogDao {
	private final ConnectionManager connectionManager = new ConnectionManager();

	public void insert(String start, String end) throws SQLException {
		String query = "INSERT INTO log (start, end) VALUES (?, ?)";
		PreparedStatement pstmt = connectionManager.getConnection().prepareStatement(query);
		pstmt.setString(1, start);
		pstmt.setString(2, end);
		pstmt.executeUpdate();
	}

	public Map<Integer, Log> selectAll() throws SQLException {
		String query = "SELECT * FROM log";
		PreparedStatement pstmt = connectionManager.getConnection().prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		Map<Integer, Log> result = new LinkedHashMap<>();
		while (rs.next()) {
			result.put(Integer.parseInt(rs.getString("log_id")), new Log(rs.getString("start"), rs.getString("end")));
		}
		return Collections.unmodifiableMap(result);
	}

	public void clear() throws SQLException {
		String query = "DELETE FROM log";
		PreparedStatement pstmt = connectionManager.getConnection().prepareStatement(query);
		pstmt.executeUpdate();
		query = "ALTER TABLE log AUTO_INCREMENT=1";
		pstmt = connectionManager.getConnection().prepareStatement(query);
		pstmt.executeUpdate();
	}
}