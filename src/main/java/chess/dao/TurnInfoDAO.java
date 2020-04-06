package chess.dao;

import static chess.dao.DBConnector.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.domain.piece.Team;

public class TurnInfoDAO {
	public void initialize(String gameId, Team team) {
		String query = "INSERT INTO turn_info VALUES (?, ?)";
		try {
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, gameId);
			pstmt.setString(2, team.getName());
			pstmt.executeUpdate();
			pstmt.close();
			closeConnection(con);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public Team findCurrent(String gameId) {
		String query = "SELECT current_team FROM turn_info WHERE game_id = ?";
		try {
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, gameId);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			Team team = Team.of(rs.getString("current_team"));
			pstmt.close();
			closeConnection(con);

			return team;
		} catch (SQLException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public void updateNext(String gameId) {
		String query = "UPDATE turn_info SET current_team = ? WHERE game_id = ?";
		try {
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, findCurrent(gameId).next().getName());
			pstmt.setString(2, gameId);
			pstmt.executeUpdate();
			pstmt.close();
			closeConnection(con);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public void truncate() {
		String query = "TRUNCATE turn_info";
		try {
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt.close();
			closeConnection(con);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
