package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PieceOnBoardDAO {
    public void addPiece(ChessBoard chessBoard, List<PieceOnBoard> pieceOnBoards) {
        try (Connection con = ConnectionManager.getConnection()) {
            for (PieceOnBoard pieceOnBoard : pieceOnBoards) {
                String query = "INSERT INTO pieceOnBoard (position, pieceType, team, chessBoardId) "
                        + "VALUES(?, ?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, pieceOnBoard.getPosition());
                pstmt.setString(2, pieceOnBoard.getPieceType());
                pstmt.setString(3, pieceOnBoard.getTeam());
                pstmt.setInt(4, chessBoard.getChessBoardId());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePiece(PieceOnBoard pieceOnBoard) {
        if (pieceOnBoard == null) {
            return;
        }
        String query = "DELETE FROM pieceOnBoard WHERE pieceId = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, pieceOnBoard.getPieceId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PieceOnBoard> findPiece(ChessBoard chessBoard) {
        List<PieceOnBoard> pieceOnBoards = new ArrayList<>();
        String query = "SELECT * FROM pieceOnBoard WHERE chessBoardId = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);) {
            pstmt.setInt(1, chessBoard.getChessBoardId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                PieceOnBoard pieceOnBoard = new PieceOnBoard(
                        rs.getInt("pieceId"),
                        rs.getString("position"),
                        rs.getString("pieceType"),
                        rs.getString("team"),
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
        String query = "UPDATE pieceOnBoard SET position = ? WHERE pieceId = ?";
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
