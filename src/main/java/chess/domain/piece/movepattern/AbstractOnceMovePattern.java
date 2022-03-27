package chess.domain.piece.movepattern;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import java.util.List;

public abstract class AbstractOnceMovePattern implements MovePattern {

    private static final int ONCE_TIME = 1;

    @Override
    public boolean canMove(Position src, Position dest) {
        return findDirection(src, dest) != null;
    }

    @Override
    public Direction findDirection(Position src, Position dest) {
        List<Direction> directions = getDirections();
        return directions.stream()
                .filter(direction -> src.canMoveByTime(direction, dest, ONCE_TIME))
                .findFirst()
                .orElse(null);
    }

    @Override
    public abstract List<Direction> getDirections();
}
