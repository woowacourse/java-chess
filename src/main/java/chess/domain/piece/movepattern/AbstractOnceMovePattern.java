package chess.domain.piece.movepattern;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import java.util.List;

public abstract class AbstractOnceMovePattern implements MovePattern {

    private static final int ONCE_TIME = 1;

    @Override
    public boolean canMove(Position source, Position destination) {
        return findDirection(source, destination) != null;
    }

    @Override
    public Direction findDirection(Position source, Position destination) {
        List<Direction> directions = getDirections();
        return directions.stream()
                .filter(direction -> source.canMoveByTime(direction, destination, ONCE_TIME))
                .findFirst()
                .orElse(null);
    }

    @Override
    public abstract List<Direction> getDirections();
}
