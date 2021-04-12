package chess.domain.dao;

import chess.domain.dto.PieceDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PieceDao {

    private static final int SINGLE_BOARD_NUMBER  = 1;

    private DBConnector dbConnector = new DBConnector();

    public void addPiece(PieceDTO pieceDTO) throws SQLException {
        String query = "INSERT INTO piece(board_id, piece_kind, piece_location) VALUES(?, ?, ?)";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, SINGLE_BOARD_NUMBER);
            pstmt.setString(2, pieceDTO.getPieceKind());
            pstmt.setString(3, pieceDTO.getLocation());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePiece(String location, int boardNumber) throws SQLException {
        String query = "DELETE FROM piece WHERE (board_id = ?) AND (piece_location = ?)";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, SINGLE_BOARD_NUMBER);
            pstmt.setString(2, location);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetPiece(int boardNumber) throws SQLException {
        String query = "DELETE FROM piece WHERE board_id = ?";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, SINGLE_BOARD_NUMBER);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PieceDTO pieceOnLocation(String location, int boardNumber) throws SQLException {
        String query = "SELECT * FROM piece WHERE (board_id = ?) AND (piece_location = ?)";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, SINGLE_BOARD_NUMBER);
            pstmt.setString(2, location);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) return null;
            return new PieceDTO(
                rs.getString("piece_location"),
                rs.getString("piece_kind")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
