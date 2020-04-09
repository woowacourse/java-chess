package chess.dao;

import chess.web.ConnectionManager;
import chess.web.MovingPosition;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class HistoryDao {
	private final ConnectionManager connectionManager = new ConnectionManager();

	public void insert(String start, String end) throws SQLException {
		String query = "INSERT INTO history (start, end) VALUES (?, ?)";
		PreparedStatement pstmt = connectionManager.getConnection().prepareStatement(query);
		pstmt.setString(1, start);
		pstmt.setString(2, end);
		pstmt.executeUpdate();
	}

	public List<MovingPosition> selectAll() throws SQLException {
		String query = "SELECT * FROM history";
		PreparedStatement pstmt = connectionManager.getConnection().prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		List<MovingPosition> result = new ArrayList<>();
		while (rs.next()) {
			result.add(new MovingPosition(rs.getString("start"), rs.getString("end")));
		}
		return Collections.unmodifiableList(result);
	}

	public void clear() throws SQLException {
		String query = "DELETE FROM history";
		PreparedStatement pstmt = connectionManager.getConnection().prepareStatement(query);
		pstmt.executeUpdate();
	}
}