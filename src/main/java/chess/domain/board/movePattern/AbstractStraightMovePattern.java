package chess.domain.board.movePattern;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public abstract class AbstractStraightMovePattern implements MovePattern {

    public boolean canMove(Position src, Position dest) {
        return findDirection(src, dest) != null;
    }

    public Direction findDirection(Position src, Position dest) {
        List<Direction> directions = getDirections();
        return directions.stream()
                .filter(direction -> src.isCrossByMovingTo(direction, dest))
                .findFirst()
                .orElse(null);
    }

    public abstract List<Direction> getDirections();
}
