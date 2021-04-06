package chess.dao;

import chess.domain.piece.Piece;
import chess.dto.PieceDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {
    private DBManager dbManager = new DBManager();

    public void addPieces(List<PieceDto> pieces, int chessGameId) {
        try (Connection connection = dbManager.getConnection()) {
            String query = "INSERT INTO PIECE(color, name, position, chessGameId) VALUE (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            for (PieceDto piece : pieces) {
                pstmt.setString(1, piece.getColor());
                pstmt.setString(2, piece.getName());
                pstmt.setString(3, piece.getPosition());
                pstmt.setInt(4, chessGameId);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PieceDto> findAllPiecesByChessGameId(int chessGameId) {
        List<PieceDto> pieces = new ArrayList<>();
        try (Connection connection = dbManager.getConnection()) {
            String query = "SELECT color, name, position FROM PIECE WHERE chessGameId = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, chessGameId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                PieceDto pieceDto = new PieceDto(rs.getString("color"),
                        rs.getString("name"), rs.getString("position"));
                pieces.add(pieceDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieces;
    }

    public void updatePiece(int chessGameId, Piece piece) {
        try (Connection connection = dbManager.getConnection()) {
            String query = "UPDATE PIECE SET color = ?, name = ? WHERE position = ? and chessGameId = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, piece.color().name());
            pstmt.setString(2, piece.name());
            pstmt.setString(3, piece.position().key());
            pstmt.setInt(4, chessGameId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
