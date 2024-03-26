package domain.chess;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public enum Direction {
    UP(Point::moveUp, Point::canMoveUp),
    UP_RIGHT(Point::moveUpRight, Point::canMoveUpRight),
    RIGHT(Point::moveRight, Point::canMoveRight),
    DOWN_RIGHT(Point::moveDownRight, Point::canMoveDownRight),
    DOWN(Point::moveDown, Point::canMoveDown),
    DOWN_LEFT(Point::moveDownLeft, Point::canMoveDownLeft),
    LEFT(Point::moveLeft, Point::canMoveLeft),
    UP_LEFT(Point::moveUpLeft, Point::canMoveUpLeft),

    UP_UP_LEFT(Point::moveUpUpLeft, Point::canMoveUpUpLeft),

    UP_UP_RIGHT(Point::moveUpUpRight, Point::canMoveUpUpRight),
    RIGHT_UP_RIGHT(Point::moveRightUpRight, Point::canMoveRightUpRight),

    RIGHT_DOWN_RIGHT(Point::moveRightDownRight, Point::canMoveRightDownRight),

    DOWN_DOWN_LEFT(Point::moveDownDownLeft, Point::canMoveDownDownLeft),

    DOWN_DOWN_RIGHT(Point::moveDownDownRight, Point::canMoveDownDownRight),
    LEFT_UP_LEFT(Point::moveLeftUpLeft, Point::canMoveLeftUpLeft),

    LEFT_DOWN_LEFT(Point::moveLeftDownLeft, Point::canMoveLeftDownLeft),
    ;
    private final UnaryOperator<Point> movePointFunction;
    private final Predicate<Point> canMovePredicate;


    Direction(final UnaryOperator<Point> movePointFunction, final Predicate<Point> canMovePredicate) {
        this.movePointFunction = movePointFunction;
        this.canMovePredicate = canMovePredicate;
    }

    public Point movePoint(final Point point) {
        return movePointFunction.apply(point);
    }

    public boolean canNotMovePoint(final Point point) {
        return !canMovePredicate.test(point);
    }

    public boolean canMovePoint(final Point point) {
        return canMovePredicate.test(point);
    }

    public boolean isVertical() {
        return this == UP || this == DOWN;
    }

    public boolean isDiagonal() {
        return this == UP_LEFT || this == UP_RIGHT || this == DOWN_LEFT || this == DOWN_RIGHT;
    }

}
