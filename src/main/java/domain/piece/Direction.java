package domain.piece;

import domain.piece.point.Point;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public enum Direction {
    UP(point -> new Point(point.file(), point.nextRank())),
    UP_RIGHT(point -> new Point(point.nextFile(), point.nextRank())),
    RIGHT(point -> new Point(point.nextFile(), point.rank())),
    DOWN_RIGHT(point -> new Point(point.nextFile(), point.prevRank())),
    DOWN(point -> new Point(point.file(), point.prevRank())),
    DOWN_LEFT(point -> new Point(point.prevFile(), point.prevRank())),
    LEFT(point -> new Point(point.prevFile(), point.rank())),
    UP_LEFT(point -> new Point(point.prevFile(), point.nextRank()));
    private Function<Point, Point> function;

    Direction(final UnaryOperator<Point> function) {
        this.function = function;
    }

    public Point move(Point point) {
        return function.apply(point);
    }
}
