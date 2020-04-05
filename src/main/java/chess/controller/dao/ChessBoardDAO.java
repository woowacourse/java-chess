package chess.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessBoardDAO {
    public void addChessBoard() throws SQLException {
        String query = "INSERT INTO chessBoard VALUES()";
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.executeUpdate();
        ConnectionManager.closeConnection(con);
    }

    public ChessBoard findRecentChessBoard() throws SQLException {
        String query = "SELECT * FROM chessBoard ORDER BY chessBoardId DESC limit 1";
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            ConnectionManager.closeConnection(con);
            return null;
        }

        ChessBoard chessBoard = new ChessBoard(rs.getInt("chessBoardId"));
        ConnectionManager.closeConnection(con);
        return chessBoard;
    }

    public void deleteChessBoard(ChessBoard chessBoard) throws SQLException {
        if (chessBoard == null) {
            return;
        }

        String query = "DELETE FROM chessBoard WHERE chessBoardId = ?";
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, chessBoard.getChessBoardId());
        pstmt.executeUpdate();
        ConnectionManager.closeConnection(con);
    }
}
