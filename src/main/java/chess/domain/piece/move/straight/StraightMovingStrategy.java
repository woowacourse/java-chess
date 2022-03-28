package chess.domain.piece.move.straight;

import java.util.List;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.move.MovingStrategy;
import chess.domain.piece.move.StraightDirection;

public class StraightMovingStrategy implements MovingStrategy {

    private final List<StraightDirection> directions;
    private final Distance distance;

    public StraightMovingStrategy(List<StraightDirection> directions, Distance distance) {
        this.directions = directions;
        this.distance = distance;
    }

    @Override
    public boolean move(Board board, Point from, Point to) {
        StraightDirection direction = StraightDirection.find(from, to);
        if (!isRightDirection(direction, from, to)) {
            return false;
        }
        int distance = this.distance.getDistance();

        Point next;
        do {
            next = from.next(direction);
            distance--;
            from = next;
        } while (isMovable(board, to, distance, next));
        return next.equals(to);
    }

    private boolean isMovable(Board board, Point to, int distance, Point next) {
        return distance > 0 && !next.equals(to) && isEmpty(board, next);
    }

    private boolean isEmpty(Board board, Point point) {
        return board.isEmpty(point);
    }

    private boolean isRightDirection(StraightDirection direction, Point from, Point to) {
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
}
