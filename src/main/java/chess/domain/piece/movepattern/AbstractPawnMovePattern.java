package chess.domain.piece.movepattern;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.piece.Color;
import java.util.List;

public abstract class AbstractPawnMovePattern implements MovePattern {

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
    public final boolean canMove(Position src, Position dest) {
        return findDirection(src, dest) != null;
    }

    @Override
    public final Direction findDirection(Position src, Position dest) {
        List<Direction> directions = getDirections();
        return directions.stream()
                .filter(direction -> canArrive(direction, src, dest))
                .findFirst()
                .orElse(null);
    }

    private boolean canArrive(Direction direction, Position src, Position dest) {
        return canArriveByOnce(direction, src, dest) || canArriveByTwice(direction, src, dest);
    }

    private boolean canArriveByOnce(Direction direction, Position src, Position dest) {
        return src.canMoveByTime(direction, dest, ONCE_TIME);
    }

    private boolean canArriveByTwice(Direction direction, Position src, Position dest) {
        List<Direction> northAndSouth = List.of(Direction.NORTH, Direction.SOUTH);

        return isStartRow(src) && northAndSouth.contains(direction)
                && src.canMoveByTime(direction, dest, TWICE_TIME);
    }

    private boolean isStartRow(Position src) {
        return src.isSameRow(getStartRow());
    }

    protected abstract Row getStartRow();

    @Override
    public abstract List<Direction> getDirections();
}
