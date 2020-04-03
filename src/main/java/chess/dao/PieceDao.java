package chess.dao;

import chess.domains.board.BoardFactory;
import chess.domains.piece.Piece;
import chess.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PieceDao {
    private final Connection conn;

    public PieceDao() {
        this.conn = JdbcUtil.getConnection();
    }

    public void addPiece(String user_id, String position, Piece piece) throws SQLException {
        String query = "INSERT INTO GAME_HISTORY VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, user_id);
        pstmt.setString(2, position);
        pstmt.setString(3, piece.name());
        pstmt.executeUpdate();
    }

    public Piece findPieceByPosition(String user_id, String position) throws SQLException {
        String query = "SELECT * FROM GAME_HISTORY WHERE USER_ID = ? and POSITION = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, user_id);
        pstmt.setString(2, position);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        String name = rs.getString("piece");
        return BoardFactory.findPieceByPieceName(name);
    }

    public void updatePiece(String user_id, String position, Piece piece) throws SQLException {
        String query = "UPDATE GAME_HISTORY SET PIECE = ? WHERE USER_ID = ? AND POSITION = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, piece.name());
        pstmt.setString(2, user_id);
        pstmt.setString(3, position);
        pstmt.executeUpdate();
    }

    public void deletePiece(String user_id, String position) throws SQLException {
        String query = "DELETE FROM GAME_HISTORY WHERE USER_ID = ? AND POSITION = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, user_id);
        pstmt.setString(2, position);
        pstmt.executeUpdate();
    }
}
