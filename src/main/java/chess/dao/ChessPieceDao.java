package chess.dao;

import chess.domains.piece.Piece;
import chess.domains.position.Position;
import chess.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessPieceDao implements PieceDao {
    @Override
    public int countSavedInfo(String user_id) {
        String query = "SELECT COUNT(*) FROM SAVED WHERE USER_ID = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user_id);
            rs = pstmt.executeQuery();
            return rs.getInt("count(*)");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }
    }

    @Override
    public void addPiece(String user_id, Position position, Piece piece) {
        String query = "INSERT INTO SAVED VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user_id);
            pstmt.setString(2, position.name());
            pstmt.setString(3, piece.name());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, pstmt);
        }
    }

    @Override
    public String findPieceNameByPosition(String user_id, Position position) {
        String query = "SELECT * FROM SAVED WHERE USER_ID = ? and POSITION = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user_id);
            pstmt.setString(2, position.name());
            rs = pstmt.executeQuery();
            return rs.getString("piece");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }
    }

    @Override
    public void updatePiece(String user_id, Position position, Piece piece) {
        String query = "UPDATE SAVED SET PIECE = ? WHERE USER_ID = ? AND POSITION = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, piece.name());
            pstmt.setString(2, user_id);
            pstmt.setString(3, position.name());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, pstmt);
        }
    }

    @Override
    public void deleteSavedInfo(String user_id) {
        String query = "DELETE FROM SAVED WHERE USER_ID = ?";

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
