package chess.domain.piece.move.straight;

import java.util.List;

import chess.domain.board.EmptyPoints;
import chess.domain.board.Route;
import chess.domain.piece.move.MovingStrategy;

public class StraightMovingStrategy implements MovingStrategy {

    private final List<StraightDirection> directions;
    private Distance distance;

    public StraightMovingStrategy(List<StraightDirection> directions, Distance distance) {
        this.directions = directions;
        this.distance = distance;
    }

    @Override
    public boolean move(Route route, EmptyPoints emptyPoints) {
        StraightDirection direction = StraightDirection.find(route);
        if (!isCorrectDirection(direction, route)) {
            return false;
        }
        do {
            route = route.moveSource(direction);
            distance = distance.decreaseOne();
        } while (distance.isLeft() && !route.isArrived() && emptyPoints.contains(route.getSource()));
        return route.isArrived();
    }

    private boolean isCorrectDirection(StraightDirection direction, Route route) {
        return this.directions.contains(direction) && isExactDirection(direction, route);
    }

    private boolean isExactDirection(StraightDirection direction, Route route) {
        return direction.isCross() || isExactDiagonal(route);
    }

    private boolean isExactDiagonal(Route route) {
        return Math.abs(route.subtractHorizontal()) == Math.abs(route.subtractVertical());
    }
}
