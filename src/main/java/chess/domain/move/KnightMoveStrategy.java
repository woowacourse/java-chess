package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import java.util.List;

public final class KnightMoveStrategy extends FirstRowMoveStrategy {

    private static final List<MovePattern> MOVE_PATTERNS = List.of(
            MovePattern.NNW,
            MovePattern.NNE,
            MovePattern.EEN,
            MovePattern.EES,
            MovePattern.SSE,
            MovePattern.SSW,
            MovePattern.WWS,
            MovePattern.WWN
    );

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Distance distance = new Distance(source, target);
        final MovePattern movePattern = MovePattern.of(distance.getHorizon(), distance.getVertical());
        if (!MOVE_PATTERNS.contains(movePattern)) {
            return false;
        }
        return isMovableToTarget(board.getPiece(target), board.getPiece(source).getColor());
    }
}
