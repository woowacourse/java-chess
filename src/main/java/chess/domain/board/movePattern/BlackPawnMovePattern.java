package chess.domain.board.movePattern;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.List;

public final class BlackPawnMovePattern extends AbstractPawnMovePattern {

    private static final Row BLACK_PAWN_START_ROW = Row.RANK_7;

    @Override
    public Direction findDirection(Position src, Position dest) {
        List<Direction> directions = getDirections();

        for (Direction direction : directions) {
            if (src.canReachByMovingTo(direction, dest, 1)) {
                return direction;
            }

            if (src.isSameRow(BLACK_PAWN_START_ROW) && direction.equals(Direction.SOUTH)
                    && src.canReachByMovingTo(direction, dest, 2)) {
                return direction;
            }
        }
        return null;
    }

    @Override
    public List<Direction> getDirections() {
        return Direction.getBlackPawnDirections();
    }
}
