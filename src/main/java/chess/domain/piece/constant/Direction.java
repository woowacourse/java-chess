package chess.domain.piece.constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.domain.Position;

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

    Direction(final int columnAmount, final int rowAmount) {
        this.columnAmount = columnAmount;
        this.rowAmount = rowAmount;
    }

    public List<Position> calculateUnidirectionalRoute(final Position source, final Position target) {
        return calculateRouteByRecursive(source, target, new ArrayList<>());
    }

    private List<Position> calculateRouteByRecursive(final Position source,
                                                     final Position target,
                                                     final List<Position> route) {
        if (source.equals(target)) {
            return route;
        }
        if (source.isNotMovableWith(columnAmount, rowAmount)) {
            return Collections.emptyList();
        }
        final Position movePosition = source.move(columnAmount, rowAmount);
        route.add(movePosition);
        return calculateRouteByRecursive(movePosition, target, route);
    }
}