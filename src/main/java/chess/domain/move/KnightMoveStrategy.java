package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import java.util.List;

public final class KnightMoveStrategy extends FirstRowMoveStrategy {

    private static final List<MovePattern> MOVE_PATTERNS = List.of(
            MovePattern.NORTH_NORTH_WEST,
            MovePattern.NORTH_NORTH_EAST,
            MovePattern.EAST_EAST_NORTH,
            MovePattern.EAST_EAST_SOUTH,
            MovePattern.SOUTH_SOUTH_EAST,
            MovePattern.SOUTH_SOUTH_WEST,
            MovePattern.WEST_WEST_SOUTH,
            MovePattern.WEST_WEST_NORTH
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
