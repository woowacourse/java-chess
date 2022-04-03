package chess.console.domain.piece;

import static chess.console.domain.board.Direction.NOTHING;

import chess.console.domain.board.Direction;
import chess.console.domain.board.Position;
import chess.console.domain.board.Rank;
import java.util.List;

public abstract class AbstractPawnPiece extends Piece {

    public static final int ONCE_TIME = 1;
    public static final int TWICE_TIME = 2;

    AbstractPawnPiece(Color color, List<Direction> directions) {
        super(color, directions);
    }

    @Override
    public final boolean canMove(Position source, Position destination) {
        return findDirection(source, destination) != NOTHING;
    }

    @Override
    public final Direction findDirection(Position source, Position destination) {
        return directions.stream()
                .filter(direction -> canArrive(direction, source, destination))
                .findFirst()
                .orElse(NOTHING);
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
        return source.isSameRank(getStartRow());
    }

    abstract Rank getStartRow();
}
