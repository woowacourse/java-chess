package chess.dao;

import chess.dto.TileDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PieceDAO {
    public void addPiece(ChessBoard chessBoard, List<TileDTO> tileDtos) {
        try (Connection con = ConnectionManager.getConnection();) {
            for (TileDTO tileDto : tileDtos) {
                String query = "INSERT INTO piece (position, pieceImageUrl, chessBoardId) "
                        + "VALUES(?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, tileDto.getPosition());
                pstmt.setString(2, tileDto.getPieceImageUrl());
                pstmt.setInt(3, chessBoard.getChessBoardId());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePiece(PieceOnBoard piece) {
        if (piece == null) {
            return;
        }
        String query = "DELETE FROM piece WHERE pieceId = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, piece.getPieceId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PieceOnBoard> findPiece(ChessBoard chessBoard) {
        List<PieceOnBoard> pieceOnBoards = new ArrayList<>();
        String query = "SELECT * FROM piece WHERE chessBoardId = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);) {
            pstmt.setInt(1, chessBoard.getChessBoardId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                PieceOnBoard pieceOnBoard = new PieceOnBoard(
                        rs.getInt("pieceId"),
                        rs.getString("position"),
                        rs.getString("pieceImageUrl"),
                        rs.getInt("chessBoardId")
                );
                pieceOnBoards.add(pieceOnBoard);
            }
            ConnectionManager.closeResultSet(rs);
            return Collections.unmodifiableList(pieceOnBoards);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updatePiece(PieceOnBoard pieceOnBoard, String targetPosition) {
        String query = "UPDATE piece SET position = ? WHERE pieceId = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, targetPosition);
            pstmt.setInt(2, pieceOnBoard.getPieceId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
