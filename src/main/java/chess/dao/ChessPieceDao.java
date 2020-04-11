package chess.dao;

import chess.domains.piece.Piece;
import chess.domains.position.Position;
import chess.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: 2020-04-11 SavedPiece 클래스 추가 후 select 결과로 객체 리턴?
public class ChessPieceDao implements PieceDao {
    @Override
    public int countSavedPieces(String gameId) {
        String query = "SELECT COUNT(*) FROM board_status WHERE game_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, gameId);
            rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt("count(*)");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }
    }

    @Override
    public void addPiece(String gameId, Position position, Piece piece) {
        String query = "INSERT INTO board_status VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, gameId);
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
    public String findPieceNameByPosition(String gameId, Position position) {
        String query = "SELECT * FROM board_status WHERE game_id = ? and POSITION = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, gameId);
            pstmt.setString(2, position.name());
            rs = pstmt.executeQuery();
            rs.next();
            return rs.getString("piece");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }
    }

    @Override
    public void updatePiece(String gameId, Position position, Piece piece) {
        String query = "UPDATE board_status SET piece = ? WHERE game_id = ? AND position = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, piece.name());
            pstmt.setString(2, gameId);
            pstmt.setString(3, position.name());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, pstmt);
        }
    }

    @Override
    public void deleteBoardStatus(String gameId) {
        String query = "DELETE FROM board_status WHERE game_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, gameId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, pstmt);
        }
    }
}
