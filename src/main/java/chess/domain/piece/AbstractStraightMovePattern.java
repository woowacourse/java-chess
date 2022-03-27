package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;

import java.util.List;

public abstract class AbstractStraightMovePattern {

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
