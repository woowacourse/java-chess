package chess.dao;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.PieceName;

import java.sql.SQLException;

public class MoveLogDao {

    public static void insertMove(final Board board,
                                  final Position source,
                                  final Position target,
                                  final Piece sourcePiece,
                                  final Piece targetPiece) {
        final var query = "INSERT INTO move_log(board_id, source_position, target_position, source_piece, target_piece)" +
                "VALUES(?, ?, ?, ?, ?)";
        try (final var connection = DBConnection.get()) {
            final var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, board.getId());
            preparedStatement.setString(2, source.getCoordinate());
            preparedStatement.setString(3, target.getCoordinate());
            preparedStatement.setString(4, PieceName.findByPiece(sourcePiece));
            preparedStatement.setString(5, PieceName.findByPiece(targetPiece));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
