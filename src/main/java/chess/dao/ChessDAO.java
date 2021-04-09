package chess.dao;

import chess.entity.Chess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessDAO {
    ChessConnection chessConnection = new ChessConnection(new ConnectionProperty());

    public void addChess(Chess chess) throws SQLException {
        String query = "INSERT INTO chess VALUES (?, ?, ?)";
        PreparedStatement pstmt = chessConnection.getConnection().prepareStatement(query);
        pstmt.setString(1, chess.getChessId());
        pstmt.setString(2, chess.getChess());
        pstmt.setString(3, chess.getTurn());
        pstmt.executeUpdate();
    }

    public Chess findByChessId(String chessId) throws SQLException {
        String query = "SELECT * FROM chess WHERE chess_id = ?";
        PreparedStatement pstmt = chessConnection.getConnection().prepareStatement(query);
        pstmt.setString(1, chessId);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        return new Chess(
                rs.getString("chess_id"),
                rs.getString("chess"),
                rs.getString("turn"))
                ;
    }

    public void updateChess(Chess chess, String updateChess, String updateTurn) throws SQLException {
        String query = "UPDATE chess SET chess = ?, turn = ? WHERE chess_id = ?";
        PreparedStatement pstmt = chessConnection.getConnection().prepareStatement(query);
        pstmt.setString(1, updateChess);
        pstmt.setString(2, updateTurn);
        pstmt.setString(3, chess.getChessId());
        pstmt.executeUpdate();
    }

    public void deleteChess(Chess chess) throws SQLException {
        String query = "DELETE FROM chess WHERE chess_id = ?";
        PreparedStatement pstmt = chessConnection.getConnection().prepareStatement(query);
        pstmt.setString(1, chess.getChessId());
        pstmt.executeUpdate();
    }

    public void deleteAllChess() throws SQLException {
        String query = "DELETE FROM chess";
        PreparedStatement pstmt = chessConnection.getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }
}