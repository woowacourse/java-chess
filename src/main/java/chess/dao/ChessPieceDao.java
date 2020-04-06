package chess.dao;

import chess.domains.piece.Piece;
import chess.domains.position.Position;
import chess.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessPieceDao implements PieceDao {
    private final Connection conn;

    public ChessPieceDao() {
        this.conn = JdbcUtil.getConnection();
    }

    @Override
    public int countSavedInfo(String user_id) throws SQLException {
        String query = "SELECT COUNT(*) FROM SAVED WHERE USER_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, user_id);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) return 0;
        return rs.getInt("count(*)");
    }

    @Override
    public void addPiece(String user_id, Position position, Piece piece) throws SQLException {
        String query = "INSERT INTO SAVED VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, user_id);
        pstmt.setString(2, position.name());
        pstmt.setString(3, piece.name());
        pstmt.executeUpdate();
    }

    @Override
    public String findPieceNameByPosition(String user_id, Position position) throws SQLException {
        String query = "SELECT * FROM SAVED WHERE USER_ID = ? and POSITION = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, user_id);
        pstmt.setString(2, position.name());
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        return rs.getString("piece");
    }

    @Override
    public void updatePiece(String user_id, Position position, Piece piece) throws SQLException {
        String query = "UPDATE SAVED SET PIECE = ? WHERE USER_ID = ? AND POSITION = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, piece.name());
        pstmt.setString(2, user_id);
        pstmt.setString(3, position.name());
        pstmt.executeUpdate();
    }

    @Override
    public void deleteSavedInfo(String user_id) throws SQLException {
        String query = "DELETE FROM SAVED WHERE USER_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, user_id);
        pstmt.executeUpdate();
    }
}
