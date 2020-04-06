package chess.dao;

import chess.domain.Side;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChessGameDao extends DaoTemplate {
	public int add(Side side) throws SQLException {
		String query = "INSERT INTO game(turn) VALUES (?)";
		PreparedStatement pstmt = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, side.name());
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.next()) {
			return rs.getInt(1);
		}
		throw new AssertionError();
	}

	public void updateTurn(int gameId, Side side) throws SQLException {
		String query = "UPDATE game SET turn = (?) WHERE id = (?)";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.setString(1, side.name());
		pstmt.setInt(2, gameId);
		pstmt.executeUpdate();
	}

	public void deleteByGameId(int gameId) throws SQLException {
		String query = "DELETE FROM game WHERE id = (?)";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.setInt(1, gameId);
		pstmt.executeUpdate();
	}

	public Side findTrunByGameId(int gameId) throws SQLException {
		String query = "SELECT * FROM game WHERE id = (?)";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.setInt(1, gameId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return Side.valueOf(rs.getString("turn"));
		}
		throw new IllegalArgumentException("id에 해당하는 정보가 없습니다.");
	}
}
