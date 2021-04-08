package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {
    private DBManager dbManager = new DBManager();

    public void saveAll(List<Piece> pieces, int chessGameId) {
        try (Connection connection = dbManager.getConnection()) {
            String query = "INSERT INTO piece(color, name, position, chessGameId) VALUE (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            for (Piece piece : pieces) {
                pstmt.setString(1, piece.color().name());
                pstmt.setString(2, piece.name());
                pstmt.setString(3, piece.position().key());
                pstmt.setInt(4, chessGameId);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Piece> findAllByChessGameId(int chessGameId) {
        List<Piece> pieces = new ArrayList<>();
        try (Connection connection = dbManager.getConnection()) {
            String query = "SELECT color, name, position FROM piece WHERE chessGameId = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, chessGameId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Piece piece = PieceFactory.findByInfo(rs.getString("color"),
                        rs.getString("name"), rs.getString("position"));
                pieces.add(piece);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieces;
    }

    public void update(int chessGameId, Piece piece) {
        try (Connection connection = dbManager.getConnection()) {
            String query = "UPDATE piece SET color = ?, name = ? WHERE position = ? and chessGameId = ?";
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
