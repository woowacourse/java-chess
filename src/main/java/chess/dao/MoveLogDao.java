package chess.dao;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.PieceName;

public class MoveLogDao {

    public static void insertMove(final Board board,
                                  final Position source,
                                  final Position target,
                                  final Piece sourcePiece,
                                  final Piece targetPiece) {
        final var query = "INSERT INTO " +
                "move_log(board_id, source_position, target_position, source_piece, target_piece) " +
                "VALUES(?, ?, ?, ?, ?)";

        JdbcTemplate.executeUpdate(query,
                board.getId(),
                source.getCoordinate(),
                target.getCoordinate(),
                PieceName.findByPiece(sourcePiece),
                PieceName.findByPiece(targetPiece)
        );
    }
}
