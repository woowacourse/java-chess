package chess.domain.board;

import java.util.ArrayList;
import java.util.List;

public enum Direction {

    TOP(0, 1),
    DOWN(0, -1),
    RIGHT(1, 0),
    LEFT(-1, 0),

    TOP_RIGHT(1, 1),
    TOP_LEFT(-1, 1),
    DOWN_RIGHT(1, -1),
    DOWN_LEFT(-1, -1),

    TOP_TOP_RIGHT(1, 2),
    TOP_TOP_LEFT(-1, 2),

    TOP_RIGHT_RIGHT(2, 1),
    TOP_LEFT_LEFT(-2, 1),

    DOWN_DOWN_RIGHT(1, -2),
    DOWN_DOWN_LEFT(-1, -2),

    DOWN_RIGHT_RIGHT(2, -1),
    DOWN_LEFT_LEFT(-2, -1),
    ;

    private final int column;
    private final int row;

    Direction(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public List<Position> route(Position from, Position to) {
        if (isNotMovable(from, to)) {
            return new ArrayList<>();
        }

        return calculateRoute(from, to, new ArrayList<>());
    }

    private boolean isNotMovable(Position from, Position to) {
        return from.equals(to) || !isMovableByMultiple(from, to);
    }

    private boolean isMovableByMultiple(Position from, Position to) {
        if (from.equals(to)) {
            return true;
        }
        if (from.isMovable(column, row)) {
            return isMovableByMultiple(from.move(column, row), to);
        }

        return false;
    }

    private List<Position> calculateRoute(Position from, Position to, List<Position> route) {
        if (from.equals(to)) {
            return route;
        }
        final Position movePosition = from.move(column, row);
        route.add(movePosition);

        return calculateRoute(movePosition, to, route);
    }
}
