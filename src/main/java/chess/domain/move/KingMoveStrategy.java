package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import java.util.List;

public final class KingMoveStrategy extends FirstRowMoveStrategy {

    private static final List<MovePattern> MOVE_PATTERNS = List.of(
            MovePattern.NORTH,
            MovePattern.NORTHEAST,
            MovePattern.EAST,
            MovePattern.SOUTHEAST,
            MovePattern.SOUTH,
            MovePattern.SOUTHWEST,
            MovePattern.WEST,
            MovePattern.NORTHWEST
    );

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Distance distance = Distance.of(source, target);
        final MovePattern movePattern = MovePattern.of(distance.getHorizon(), distance.getVertical());

        if (!MOVE_PATTERNS.contains(movePattern)) {
            return false;
        }
        return isTargetPositionMovable(board.getPiece(target), board.getPieceTeamByPosition(source));
    }
}
