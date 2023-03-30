package chess.domain.piece.direction;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Direction {
    UP(0, 1, DirectionChecker::isUp),
    UP_RIGHT(1, 1, DirectionChecker::isUpRight),
    RIGHT(1, 0, DirectionChecker::isRight),
    DOWN_RIGHT(1, -1, DirectionChecker::isDownRight),
    DOWN(0, -1, DirectionChecker::isDown),
    DOWN_LEFT(-1, -1, DirectionChecker::isDownLeft),
    LEFT(-1, 0, DirectionChecker::isLeft),
    UP_LEFT(-1, 1, DirectionChecker::isUpLeft),
    UP_UP_RIGHT(1, 2, DirectionChecker::isUpUpRight),
    UP_UP_LEFT(-1, 2, DirectionChecker::isUpUpLeft),
    RIGHT_RIGHT_UP(2, 1, DirectionChecker::isRightRightUp),
    RIGHT_RIGHT_DOWN(2, -1, DirectionChecker::isRightRightDown),
    DOWN_DOWN_RIGHT(1, -2, DirectionChecker::isDownDownRight),
    DOWN_DOWN_LEFT(-1, -2, DirectionChecker::isDownDownLeft),
    LEFT_LEFT_UP(-2, 1, DirectionChecker::isLeftLeftUp),
    LEFT_LEFT_DOWN(-2, -1, DirectionChecker::isLeftLeftDown);

    final int x;
    final int y;
    private final BiPredicate<Integer, Integer> condition;

    Direction(final int x, final int y, final BiPredicate<Integer, Integer> condition) {
        this.x = x;
        this.y = y;
        this.condition = condition;
    }

    public static Direction of(int xDiff, int yDiff) {
        return Arrays.stream(values())
                .filter(direction -> direction.condition.test(xDiff, yDiff))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 방향입니다."));
    }

    public int getNextXPoint(int xPoint) {
        return this.x + xPoint;
    }

    public int getNextYPoint(int yPoint) {
        return this.y + yPoint;
    }

    public boolean isHorizontal() {
        return x != 0 && y == 0;
    }

    public boolean isVertical() {
        return x == 0 && y != 0;
    }

    public boolean isDiagonal() {
        return x != 0 && y != 0;
    }

    public Direction getDirectionBeforeMoveSideways() {
        if (y == 2) {
            return UP;
        }
        if (y == -2) {
            return DOWN;
        }
        if (x == 2) {
            return RIGHT;
        }
        return LEFT;
    }
}
