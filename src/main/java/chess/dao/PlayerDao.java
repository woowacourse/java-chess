package chess.dao;

import chess.domain.Team;
import chess.domain.Turn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDao {
	public Turn findTurn(int playerId) throws SQLException, ClassNotFoundException {
		String query = "select  from player where player_id = (?)";
		try (Connection con = ConnectionLoader.load();
			 PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setInt(1, playerId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (!rs.next()) {
					throw new IllegalArgumentException("Turn이 잘못되었습니다.");
				}
				String team = rs.getString("team");
				return new Turn(Team.of(team));
			}
		}
	}

	public int create(String name, String password) throws SQLException, ClassNotFoundException {
		String query = "insert into player(name, password, team) value (?, ?, 'WHITE')";
		try (Connection con = ConnectionLoader.load(); PreparedStatement pstmt = con.prepareStatement(query,
			PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			pstmt.executeUpdate();
			return getId(pstmt);
		}
	}

	private int getId(PreparedStatement pstmt) throws SQLException {
		try (ResultSet rs = pstmt.getGeneratedKeys()) {
			if (rs.next()) {
				return rs.getInt(1);
			}
			throw new IllegalArgumentException();
		}
	}
}
