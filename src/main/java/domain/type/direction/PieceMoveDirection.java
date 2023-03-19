package domain.type.direction;

import domain.Location;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public enum PieceMoveDirection {

    RIGHT_UP(1, 1, PieceMoveDirectionChecker::isRightUpDiagonal),
    RIGHT_DOWN(1, -1, PieceMoveDirectionChecker::isRightDownDiagonal),
    LEFT_UP(-1, 1, PieceMoveDirectionChecker::isLeftUpDiagonal),
    LEFT_DOWN(-1, -1, PieceMoveDirectionChecker::isLeftDownDiagonal),
    RIGHT(1, 0, PieceMoveDirectionChecker::isRight),
    LEFT(-1, 0, PieceMoveDirectionChecker::isLeft),
    DOWN(0, -1, PieceMoveDirectionChecker::isDown),
    UP(0, 1, PieceMoveDirectionChecker::isUp),
    UP_UP_LEFT(-2, -1, PieceMoveDirectionChecker::isTwoUpAndLeft),
    UP_UP_RIGHT(-2, 1, PieceMoveDirectionChecker::isTwoUpAndRight),
    LEFT_LEFT_UP(-1, -2, PieceMoveDirectionChecker::isTwoLeftAndUp),
    RIGHT_RIGHT_UP(-1, 2, PieceMoveDirectionChecker::isTwoRightAndUp),
    DOWN_DOWN_LEFT(2, -1, PieceMoveDirectionChecker::isTwoDownAndLeft),
    DOWN_DOWN_RIGHT(2, 1, PieceMoveDirectionChecker::isTwoDownAndRight),
    LEFT_LEFT_DOWN(1, -2, PieceMoveDirectionChecker::isTwoLeftAndDown),
    RIGHT_RIGHT_DOWN(1, 2, PieceMoveDirectionChecker::isTwoRightAndDown);
    public static final List<PieceMoveDirection> EIGHT_CARDINAL_DIRECTION = List.of(
        PieceMoveDirection.UP,
        PieceMoveDirection.DOWN,
        PieceMoveDirection.LEFT,
        PieceMoveDirection.RIGHT,
        PieceMoveDirection.RIGHT_UP,
        PieceMoveDirection.RIGHT_DOWN,
        PieceMoveDirection.LEFT_UP,
        PieceMoveDirection.LEFT_DOWN
    );

    private final int colDiff;
    private final int rowDiff;
    private final BiPredicate<Location, Location> condition;

    PieceMoveDirection(final int colDiff, final int rowDiff, final BiPredicate<Location, Location> condition) {
        this.colDiff = colDiff;
        this.rowDiff = rowDiff;
        this.condition = condition;
    }

    public static PieceMoveDirection find(final Location start, final Location end) {
        return Arrays.stream(PieceMoveDirection.values())
            .filter(direction -> direction.condition.test(start, end))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isEightCardinalDirection() {
        return EIGHT_CARDINAL_DIRECTION.contains(this);
    }

    public int getColDiff() {
        return colDiff;
    }

    public int getRowDiff() {
        return rowDiff;
    }
}
