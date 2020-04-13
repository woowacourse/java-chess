package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessBoardDAO {
    public void addChessBoard() {
        String query = "INSERT INTO chessBoard VALUES()";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ChessBoard findRecentChessBoard() {
        String query = "SELECT * FROM chessBoard ORDER BY chessBoardId DESC limit 1";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (!rs.next()) {
                return null;
            }

            ChessBoard chessBoard = new ChessBoard(
                    rs.getInt("chessBoardId")
            );
            ConnectionManager.closeResultSet(rs);
            return chessBoard;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteChessBoard(ChessBoard chessBoard) {
        if (chessBoard == null) {
            return;
        }
        String query = "DELETE FROM chessBoard WHERE chessBoardId = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);) {
            pstmt.setInt(1, chessBoard.getChessBoardId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ChessBoard findById(int chessBoardId) {
        String query = "SELECT * FROM chessBoard WHERE chessBoardId=?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, chessBoardId);
            ResultSet rs = pstmt.executeQuery();
            while (!rs.next()) {
                return null;
            }

            ChessBoard chessBoard = new ChessBoard(
                    rs.getInt("chessBoardId")
            );
            ConnectionManager.closeResultSet(rs);
            return chessBoard;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
