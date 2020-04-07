package chess.dao;

import chess.domains.piece.PieceColor;
import chess.domains.position.Position;
import chess.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MoveHistoryDao {
    public void addMoveHistory(String user_id, PieceColor team, Position source, Position target) {
        String query = "INSERT INTO MOVE_HISTORY VALUES (?, (SELECT IFNULL(MAX(MOVES) + 1, 1) FROM MOVE_HISTORY AS INNERTABLE WHERE USER_ID = ?), ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user_id);
            pstmt.setString(2, user_id);
            pstmt.setString(3, team.name());
            pstmt.setString(4, source.name());
            pstmt.setString(5, target.name());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, pstmt);
        }
    }

    public Optional<String> figureLastTurn(String user_id) {
        String query = "SELECT TEAM FROM MOVE_HISTORY WHERE USER_ID = ? ORDER BY MOVES DESC LIMIT 1";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user_id);
            rs = pstmt.executeQuery();
            return Optional.ofNullable(rs.getString("TEAM"));
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }
    }

    public void deleteMoveHistory(String user_id) {
        String query = "DELETE FROM MOVE_HISTORY WHERE USER_ID = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, pstmt);
        }
    }

}
