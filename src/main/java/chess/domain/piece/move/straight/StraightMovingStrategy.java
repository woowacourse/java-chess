package chess.domain.piece.move.straight;

import java.util.List;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.move.MovingStrategy;

public class StraightMovingStrategy implements MovingStrategy {

    private final List<StraightDirection> directions;
    private Distance distance;

    public StraightMovingStrategy(List<StraightDirection> directions, Distance distance) {
        this.directions = directions;
        this.distance = distance;
    }

    @Override
    public boolean move(Board board, Point from, Point to) {
        StraightDirection direction = StraightDirection.find(from, to);
        if (!isCorrectDirection(direction, from, to)) {
            return false;
        }

        Point next;
        do {
            next = from.next(direction);
            distance.decreaseOne();
            from = next;
        } while (distance.isLeft() && isMovable(to, next) && board.isEmpty(next));
        return !isMovable(to, next);
    }

    private boolean isCorrectDirection(StraightDirection direction, Point from, Point to) {
        return this.directions.contains(direction) && isExactDirection(direction, from, to);
    }

    private boolean isExactDirection(StraightDirection direction, Point from, Point to) {
        if (direction.isCross()) {
            return true;
        }
        return isExactDiagonal(from, to);
    }

    private boolean isExactDiagonal(Point from, Point to) {
        return Math.abs(from.subtractVertical(to)) == Math.abs(from.subtractHorizontal(to));
    }

    private boolean isMovable(Point to, Point next) {
        return !next.equals(to);
    }
}
