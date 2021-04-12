package chess.dao;

import chess.entity.Chess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessDAO {
    ChessConnection manager = ChessConnection.getInstance();

    public void addChess(Chess chess) throws SQLException {
        Connection connection = manager.getConnection();
        PreparedStatement pstmt = null;
        try {
            String query = "INSERT INTO chess VALUES (?, ?, ?)";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, chess.getChessId());
            pstmt.setString(2, chess.getChess());
            pstmt.setString(3, chess.getTurn());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            ChessConnection.logger.error("{}", e.getMessage());
        } finally {
            ChessConnection.getInstance().freeConnection(connection, pstmt);
        }
    }

    public Chess findByChessId(String chessId) throws SQLException {
        Connection connection = manager.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Chess chess = null;
        try {
            String query = "SELECT * FROM chess WHERE chess_id = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, chessId);
            rs = pstmt.executeQuery();
            if (!rs.next()) return null;
            chess = new Chess(
                    rs.getString("chess_id"),
                    rs.getString("chess"),
                    rs.getString("turn"))
                    ;
        } catch (SQLException e) {
            ChessConnection.logger.error("{}", e.getMessage());
        } finally {
            ChessConnection.getInstance().freeConnection(connection, pstmt, rs);
        }
        return chess;
    }

    public void updateChess(Chess chess, String updateChess, String updateTurn) throws SQLException {
        Connection connection = manager.getConnection();
        PreparedStatement pstmt = null;
        try {
            String query = "UPDATE chess SET chess = ?, turn = ? WHERE chess_id = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, updateChess);
            pstmt.setString(2, updateTurn);
            pstmt.setString(3, chess.getChessId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            ChessConnection.logger.error("{}", e.getMessage());
        } finally {
            ChessConnection.getInstance().freeConnection(connection, pstmt);
        }
    }

    public void deleteChess(Chess chess) throws SQLException {
        Connection connection = manager.getConnection();
        PreparedStatement pstmt = null;
        try {
            String query = "DELETE FROM chess WHERE chess_id = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, chess.getChessId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            ChessConnection.logger.error("{}", e.getMessage());
        } finally {
            ChessConnection.getInstance().freeConnection(connection, pstmt);
        }

    }

    public void deleteAllChess() throws SQLException {
        Connection connection = manager.getConnection();
        PreparedStatement pstmt = null;
        try {
            String query = "DELETE FROM chess";
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            ChessConnection.logger.error("{}", e.getMessage());
        } finally {
            ChessConnection.getInstance().freeConnection(connection, pstmt);
        }
    }
}