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
    UP_UP_LEFT(-1, 2, PieceMoveDirectionChecker::isTwoUpAndLeft),
    UP_UP_RIGHT(1, 2, PieceMoveDirectionChecker::isTwoUpAndRight),
    DOWN_DOWN_LEFT(-1, -2, PieceMoveDirectionChecker::isTwoDownAndLeft),
    DOWN_DOWN_RIGHT(1, -2, PieceMoveDirectionChecker::isTwoDownAndRight),
    LEFT_LEFT_UP(-2, 1, PieceMoveDirectionChecker::isTwoLeftAndUp),
    LEFT_LEFT_DOWN(-2, -1, PieceMoveDirectionChecker::isTwoLeftAndDown),
    RIGHT_RIGHT_UP(2, 1, PieceMoveDirectionChecker::isTwoRightAndUp),
    RIGHT_RIGHT_DOWN(2, -1, PieceMoveDirectionChecker::isTwoRightAndDown);
    private static final List<PieceMoveDirection> KNIGHT_MOVE_DIRECTION = List.of(
        PieceMoveDirection.UP_UP_LEFT,
        PieceMoveDirection.UP_UP_RIGHT,
        PieceMoveDirection.DOWN_DOWN_LEFT,
        PieceMoveDirection.DOWN_DOWN_RIGHT,
        PieceMoveDirection.LEFT_LEFT_UP,
        PieceMoveDirection.LEFT_LEFT_DOWN,
        PieceMoveDirection.RIGHT_RIGHT_UP,
        PieceMoveDirection.RIGHT_RIGHT_DOWN
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

    public boolean isNightMoveDirection() {
        return KNIGHT_MOVE_DIRECTION.contains(this);
    }

    public int getColDiff() {
        return colDiff;
    }

    public int getRowDiff() {
        return rowDiff;
    }
}
