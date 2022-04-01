package chess.domain.board.movePattern;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public abstract class AbstractSingleMovePattern implements MovePattern {

    @Override
    public final boolean canMove(Position src, Position dest) {
        return findDirection(src, dest) != null;
    }

    @Override
    public final Direction findDirection(Position src, Position dest) {
        List<Direction> directions = getDirections();

        for (Direction direction : directions) {
            if (src.canReachByMovingTo(direction, dest, 1)) {
                return direction;
            }
        }
        return null;
    }
}
