package chess.dao;

import chess.controller.dto.PieceDto;
import chess.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {

    private final DBConnection dbConnection = DBConnection.getInstance();

    public void addPiece(String pieceName, String pieceTeam, double pieceScore, String piecePosition, int boardId) throws SQLException {
        String sql = "INSERT INTO piece(name, team, score, position, board_id) VALUES (?,?,?,?,?)";
        PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql);
        pstmt.setString(1, pieceName);
        pstmt.setString(2, pieceTeam);
        pstmt.setDouble(3, pieceScore);
        pstmt.setString(4, piecePosition);
        pstmt.setInt(5, boardId);

        if (pstmt.executeUpdate() == 0) {
            throw new IllegalArgumentException("DB에 piece를 추가할 수 없습니다.");
        }
        pstmt.close();
    }

    public void updatePiece(String currentPosition, String targetPosition) throws SQLException {
        String sql = "UPDATE piece SET position = ? WHERE position = ?";
        PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql);
        pstmt.setString(1, targetPosition);
        pstmt.setString(2, currentPosition);

        if (pstmt.executeUpdate() == 0) {
            throw new IllegalArgumentException("DB에 move update 실패");
        }
        pstmt.close();
    }

    public List<PieceDto> selectAllPiece() throws SQLException {
        String sql = "SELECT * FROM piece";
        PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        List<PieceDto> pieces = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString(2);
            String teamColor = rs.getString(3);
            double score = rs.getDouble(4);
            String currentPosition = rs.getString(5);
            pieces.add(new PieceDto(name, teamColor, score, currentPosition));
        }
        rs.close();
        pstmt.close();
        return pieces;
    }

    public void deleteAll() throws SQLException {
        String sql = "DELETE FROM piece";
        PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql);
        pstmt.executeUpdate();
        pstmt.close();
    }
}
