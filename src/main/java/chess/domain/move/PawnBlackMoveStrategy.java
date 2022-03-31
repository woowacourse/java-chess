package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.List;

public final class PawnBlackMoveStrategy extends PawnMoveStrategy {

    private final static List<MovementPattern> BLACK_MOVE_PATTERNS = List.of(
            MovementPattern.SOUTH,
            MovementPattern.SE,
            MovementPattern.SW,
            MovementPattern.START_MOVEMENT_OF_BLACK_PAWN
    );

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Movement movement = new Movement(source, target);
        final MovementPattern movementPattern = MovementPattern.of(movement.getHorizon(), movement.getVertical());
        return isMovePattern(movementPattern, board, source, board.getPiece(target));
    }

    @Override
    protected boolean isMovePattern(final MovementPattern movementPattern,
                                    final Board board,
                                    final Position source,
                                    final Piece targetPiece) {
        if (!BLACK_MOVE_PATTERNS.contains(movementPattern)) {
            return false;
        }
        if (movementPattern == MovementPattern.START_MOVEMENT_OF_BLACK_PAWN) {
            return isStartMove(board, source, targetPiece, board.getTeamOfPiece(source));
        }
        if (movementPattern == MovementPattern.SOUTH) {
            return targetPiece.isBlank();
        }
        if (movementPattern == MovementPattern.SE || movementPattern == MovementPattern.SW) {
            return isCatchable(targetPiece, board.getTeamOfPiece(source));
        }
        return false;
    }
}
