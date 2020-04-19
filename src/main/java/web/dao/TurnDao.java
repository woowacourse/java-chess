package web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import web.dto.TurnDto;

public class TurnDao {
	private static final TurnDao TURN_DAO = new TurnDao();
	private static final String TABLE_NAME = "board";

	private TurnDao() {
	}

	public static TurnDao getInstance() {
		return TURN_DAO;
	}

	public void add(TurnDto turnDto) throws SQLException {
		String query = "INSERT INTO " + TABLE_NAME + " VALUES(?)";
		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query)
		) {
			pstmt.setString(1, turnDto.getTurn());
			pstmt.executeUpdate();
		}
	}

	public TurnDto find() throws SQLException {
		String query = "SELECT * FROM " + TABLE_NAME;
		ResultSet rs;
		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query)
		) {
			rs = pstmt.executeQuery();
			if (!rs.next())
				return new TurnDto("");
			return new TurnDto(rs.getString("turn"));
		}
	}

	public void update(TurnDto turnDto) throws SQLException {
		String query = "UPDATE " + TABLE_NAME + " SET turn = ?";
		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query)
		) {
			pstmt.setString(1, turnDto.getTurn());
			pstmt.executeUpdate();
		}
	}
}
