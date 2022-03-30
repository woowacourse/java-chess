package chess.domain.direction;

import chess.domain.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Direction {

    UP(0, 1),
    DOWN(0, -1),
    RIGHT(1, 0),
    LEFT(-1, 0),

    UP_RIGHT(1, 1),
    UP_LEFT(-1, 1),
    DOWN_RIGHT(1, -1),
    DOWN_LEFT(-1, -1),

    UP_UP_RIGHT(1, 2),
    UP_RIGHT_RIGHT(2, 1),
    DOWN_DOWN_RIGHT(1, -2),
    DOWN_RIGHT_RIGHT(2, -1),

    UP_UP_LEFT(-1, 2),
    UP_LEFT_LEFT(-2, 1),
    DOWN_DOWN_LEFT(-1, -2),
    DOWN_LEFT_LEFT(-2, -1),
    ;

    private final int columnAmount;
    private final int rowAmount;

    Direction(int columnAmount, int rowAmount) {
        this.columnAmount = columnAmount;
        this.rowAmount = rowAmount;
    }

    public Position move(Position position) {
        return position.move(columnAmount, rowAmount);
    }

    public List<Position> route(Position position, Position targetPosition) {
        if (isNotMovable(position, targetPosition)) {
            return Collections.emptyList();
        }
        return calculateRoute(position, targetPosition, new ArrayList<>());
    }

    private boolean isNotMovable(Position position, Position wantPosition) {
        return position.equals(wantPosition) || !isMovableByMultipleMovable(position, wantPosition);
    }

    private boolean isMovableByMultipleMovable(Position position, Position wantPosition) {
        if (position.equals(wantPosition)) {
            return true;
        }
        if (position.isMovable(columnAmount, rowAmount)) {
            return isMovableByMultipleMovable(move(position), wantPosition);
        }
        return false;
    }

    private List<Position> calculateRoute(Position position, Position targetPosition, List<Position> route) {
        if (position.equals(targetPosition)) {
            return route;
        }
        Position movePosition = move(position);
        route.add(movePosition);
        return calculateRoute(movePosition, targetPosition, route);
    }
}
