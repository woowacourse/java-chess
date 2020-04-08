package chess.controller.dao;

import java.sql.*;

public class ChessBoardDAO {
    public void addChessBoard() throws SQLException {
        Connection con = ConnectionManager.getConnection();
        String query = "INSERT INTO chessBoard VALUES()";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.executeUpdate();
        ConnectionManager.closeConnection(con);
    }

    public ChessBoard findRecentChessBoard() throws SQLException {
        Connection con = ConnectionManager.getConnection();
        String query = "SELECT * FROM chessBoard ORDER BY chessBoardId DESC limit 1";
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            ConnectionManager.closeConnection(con);
            return null;
        }

        ChessBoard chessBoard = new ChessBoard(
                rs.getInt("chessBoardId")
        );
        ConnectionManager.closeConnection(con);
        return chessBoard;
    }

    public void deleteChessBoard(ChessBoard chessBoard) throws SQLException {
        if (chessBoard == null) {
            return;
        }

        Connection con = ConnectionManager.getConnection();
        String query = "DELETE FROM chessBoard WHERE chessBoardId = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, chessBoard.getChessBoardId());
        pstmt.executeUpdate();
        ConnectionManager.closeConnection(con);
    }

    public ChessBoard findById(int chessBoardId) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        String query = "SELECT * FROM chessBoard WHERE chessBoardId=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, chessBoardId);
        ResultSet rs = pstmt.executeQuery();

        while (!rs.next()) {
            return null;
        }
        ChessBoard chessBoard = new ChessBoard(
                rs.getInt("chessBoardId")
        );
        ConnectionManager.closeConnection(con);
        return chessBoard;
    }
}
