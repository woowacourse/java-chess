package chess.domain.dao;

import chess.domain.dto.PieceDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PieceDao {

    private DBConnector dbConnector = new DBConnector();

    public void addPiece(PieceDto pieceDTO, int index) {
        String query = "INSERT INTO piece(board_id, piece_kind, piece_location) VALUES(?, ?, ?)";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, index);
            pstmt.setString(2, pieceDTO.getPieceKind());
            pstmt.setString(3, pieceDTO.getLocation());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePiece(String location, int index) {
        String query = "DELETE FROM piece WHERE (board_id = ?) AND (piece_location = ?)";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, index);
            pstmt.setString(2, location);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetPiece(int index) {
        String query = "DELETE FROM piece WHERE board_id = ?";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, index);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PieceDto pieceOnLocation(String location, int index) {
        String query = "SELECT * FROM piece WHERE (board_id = ?) AND (piece_location = ?)";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, index);
            pstmt.setString(2, location);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) return null;
            return new PieceDto(
                rs.getString("piece_location"),
                rs.getString("piece_kind")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
