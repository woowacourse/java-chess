package dao;

import db.DBConnection;
import vo.PieceVO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PieceDAO {
    public void addPiece(PieceVO pieceVO) throws SQLException {
        String query = "INSERT INTO piece(game_id, name, row, col) VALUES (?, ?, ?, ?)";

        PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(query);
        pstmt.setInt(1, pieceVO.getGameId());
        pstmt.setString(2, pieceVO.getName());
        pstmt.setInt(3, pieceVO.getRow());
        pstmt.setString(4, pieceVO.getCol());

        pstmt.executeUpdate();
    }
}
