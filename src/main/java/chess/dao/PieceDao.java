package chess.dao;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceConvertor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PieceDao {
    public void save(Map<Position, Piece> board) {
        try (final Connection connection = DatabaseConnector.getConnection()) {
            for (Entry<Position, Piece> pieceEntry : board.entrySet()) {
                savePiece(pieceEntry, connection);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void savePiece(Entry<Position, Piece> pieceEntry, Connection connection) {
        final String sql = "INSERT INTO piece (piece_type, position, game_name) VALUE (?, ?, 'chess')";

        String pieceType = pieceEntry.getValue().toString();
        String position = pieceEntry.getKey().toString();

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pieceType);
            statement.setString(2, position);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Map<Position, Piece> findAllByGameName(String gameName) {
        final String sql = "SELECT * FROM piece WHERE game_name = ?";
        Map<Position, Piece> board = new HashMap<>();

        try (final Connection connection = DatabaseConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, gameName);
            try (final ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Position position = Position.of(resultSet.getString("position"));
                    Piece piece = PieceConvertor.convert(resultSet.getString("piece_type"));
                    board.put(position, piece);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return board;
    }

    public void movePiece(String from, String to, String fromPiece, String toPiece) {
        deletePieceAtTo(to, toPiece);
        updatePieceAtFrom(from, to, fromPiece, toPiece);
    }

    private void updatePieceAtFrom(String from, String to, String fromPiece, String toPiece) {
        final String sql = "UPDATE piece SET position = ? WHERE piece_type = ? AND  position = ?";

        try (final Connection connection = DatabaseConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, to);
            statement.setString(2, fromPiece);
            statement.setString(3, from);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deletePieceAtTo(String to, String toPiece) {
        final String sql = "DELETE FROM piece WHERE position = ? AND piece_type = ?";

        try (final Connection connection = DatabaseConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, to);
            statement.setString(2, toPiece);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
