package chess.controller.dao;

import chess.controller.dto.TileDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PieceDAO {
    public void addPiece(int chessBoardId, List<TileDto> tileDtos) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        for (TileDto tileDto : tileDtos) {
            String query = "INSERT INTO piece (position, pieceImageUrl, chessBoardId) "
                    + "VALUES(?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, tileDto.getPosition());
            pstmt.setString(2, tileDto.getPieceImageUrl());
            pstmt.setInt(3, chessBoardId);
            pstmt.executeUpdate();
        }
        ConnectionManager.closeConnection(con);
    }

    public void updatePiece(List<PieceOnBoard> pieces) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        for (PieceOnBoard pieceOnBoard : pieces) {
            String query = "UPDATE piece SET position = ? WHERE pieceId = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, pieceOnBoard.getPosition());
            pstmt.setInt(2, pieceOnBoard.getPieceId());
            pstmt.executeUpdate();
        }
        ConnectionManager.closeConnection(con);
    }

    public void deletePiece(PieceOnBoard a2WhitePawn) throws SQLException {
        if (a2WhitePawn == null) {
            return;
        }

        Connection con = ConnectionManager.getConnection();
        String query = "DELETE FROM piece WHERE pieceId = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, a2WhitePawn.getPieceId());
        pstmt.executeUpdate();
        ConnectionManager.closeConnection(con);
    }
}
