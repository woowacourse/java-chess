package chess.domain.position;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Direction {

    VERTICAL(Movement::isVertical, 0, 1),
    DIAGONAL_LEFT(Movement::isDiagonalLeft, -1, 1),
    DIAGONAL_RIGHT(Movement::isDiagonalRight, 1, 1),
    HORIZONTAL(Movement::isHorizontal, 1, 0);

    private final int dx;
    private final int dy;
    private final Predicate<Movement> condition;

    Direction(Predicate<Movement> condition, int dx, int dy) {
        this.condition = condition;
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction from(final Movement movement) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.condition.test(movement))
                .findFirst()
                .orElseThrow();
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
