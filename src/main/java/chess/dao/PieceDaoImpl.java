package chess.dao;

import chess.domain.Color;
import chess.domain.PieceConverter;
import chess.domain.Position;
import chess.domain.piece.Piece;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PieceDaoImpl implements PieceDao {

    private final Connection connection;

    public PieceDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Map<Position, Piece> findAllPieces() {
        final String query = "select type, color, position_col, position_row from piece";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            ResultSet resultSet = pstmt.executeQuery();
            Map<Position, Piece> pieces = new HashMap<>();
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                String colorName = resultSet.getString("color");
                char column = resultSet.getString("position_col").charAt(0);
                char row = resultSet.getString("position_row").charAt(0);

                Position position = Position.of(column, row);
                Color color = Color.valueOf(colorName);
                pieces.put(position, PieceConverter.parseToPiece(type, color));
            }
            return pieces;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void savePieces(final Map<Position, Piece> chessBoard) {
        final String query = "insert into piece (type, color, position_col, position_row) values (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            for (Position position : chessBoard.keySet()) {
                Piece piece = chessBoard.get(position);
                pstmt.setString(1, piece.name());
                pstmt.setString(2, piece.color().name());
                pstmt.setString(3, String.valueOf(position.column()));
                pstmt.setString(4, String.valueOf(position.row()));
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
