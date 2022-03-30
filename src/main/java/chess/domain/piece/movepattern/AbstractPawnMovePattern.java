package chess.domain.piece.movepattern;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.piece.Color;
import java.util.List;

public abstract class AbstractPawnMovePattern extends AbstractMovePattern {

    public static final int ONCE_TIME = 1;
    public static final int TWICE_TIME = 2;

    public static AbstractPawnMovePattern of(Color color) {
        if (color == Color.BLACK) {
            return new BlackPawnMovePattern();
        }
        if (color == Color.WHITE) {
            return new WhitePawnMovePattern();
        }
        throw new IllegalArgumentException("error");
    }

    @Override
    public final boolean canMove(Position source, Position destination) {
        return findDirection(source, destination) != null;
    }

    @Override
    public final Direction findDirection(Position source, Position destination) {
        List<Direction> directions = getDirections();
        return directions.stream()
                .filter(direction -> canArrive(direction, source, destination))
                .findFirst()
                .orElse(null);
    }

    private boolean canArrive(Direction direction, Position source, Position destination) {
        return canArriveByOnce(direction, source, destination) || canArriveByTwice(direction, source, destination);
    }

    private boolean canArriveByOnce(Direction direction, Position source, Position destination) {
        return source.canMoveByTime(direction, destination, ONCE_TIME);
    }

    private boolean canArriveByTwice(Direction direction, Position source, Position destination) {
        List<Direction> northAndSouth = List.of(Direction.NORTH, Direction.SOUTH);

        return isStartRow(source) && northAndSouth.contains(direction)
                && source.canMoveByTime(direction, destination, TWICE_TIME);
    }

    private boolean isStartRow(Position source) {
        return source.isSameRow(getStartRow());
    }

    abstract Row getStartRow();
}
