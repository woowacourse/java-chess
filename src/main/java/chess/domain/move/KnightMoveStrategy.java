package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import java.util.List;

public final class KnightMoveStrategy extends FirstRowMoveStrategy {

    private static final List<MovementPattern> MOVE_PATTERNS = List.of(
            MovementPattern.NNW,
            MovementPattern.NNE,
            MovementPattern.EEN,
            MovementPattern.EES,
            MovementPattern.SSE,
            MovementPattern.SSW,
            MovementPattern.WWS,
            MovementPattern.WWN
    );

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Movement movement = new Movement(source, target);
        final MovementPattern movementPattern = MovementPattern.of(movement.getHorizon(), movement.getVertical());
        if (!MOVE_PATTERNS.contains(movementPattern)) {
            return false;
        }
        return isMovableToTarget(board.getPiece(target), board.getPiece(source).getTeam());
    }
}
