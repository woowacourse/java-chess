package domain.piece.attribute.point;

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
    UP_LEFT(Point::moveUpLeft, Point::canMoveUpLeft);
    private final UnaryOperator<Point> movePointFunction;
    private final Predicate<Point> canMovePredicate;


    Direction(final UnaryOperator<Point> movePointFunction, final Predicate<Point> canMovePredicate) {
        this.movePointFunction = movePointFunction;
        this.canMovePredicate = canMovePredicate;
    }

    public Point movePoint(final Point point) {
        return movePointFunction.apply(point);
    }

    public boolean canMovePoint(final Point point) {
        return canMovePredicate.test(point);
    }

    public boolean isStraight() {
        return this == UP || this == DOWN;
    }

    public boolean isDiagonal() {
        return this == UP_LEFT || this == UP_RIGHT || this == DOWN_LEFT || this == DOWN_RIGHT;
    }

}
