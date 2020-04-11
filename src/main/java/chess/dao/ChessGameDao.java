package chess.dao;

import chess.domain.Side;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChessGameDao extends DaoTemplate {
	public int add(Side side) {
		String query = "INSERT INTO game(turn) VALUES (?)";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, side.name());
			pstmt.executeUpdate();
			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (!rs.next()) {
					throw new RuntimeException("기본 id가 올바르게 생성되지 않았습니다.");
				}
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException("DB 데이터 삽입 중 오류가 발생했습니다.");
		}
	}

	public void updateTurn(int gameId, Side side) {
		String query = "UPDATE game SET turn = (?) WHERE id = (?)";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			pstmt.setString(1, side.name());
			pstmt.setInt(2, gameId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("DB 업데이트 중 오류가 발생했습니다.");
		}
	}

	public void deleteByGameId(int gameId) {
		String query = "DELETE FROM game WHERE id = (?)";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			pstmt.setInt(1, gameId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("DB 데이터 삭제 중 오류가 발생했습니다.");
		}
	}

	public Side findTrunByGameId(int gameId) {
		String query = "SELECT * FROM game WHERE id = (?)";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			pstmt.setInt(1, gameId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (!rs.next()) {
					throw new IllegalArgumentException("id에 해당하는 정보가 없습니다.");
				}
				return Side.valueOf(rs.getString("turn"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("DB 검색 중 오류가 발생했습니다.");
		}
	}
}
