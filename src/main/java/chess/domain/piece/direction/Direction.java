package chess.domain.piece.direction;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Direction {
    UP(0, 1, DirectionCheckerFactory::isUp),
    UP_RIGHT(1, 1, DirectionCheckerFactory::isUpRight),
    RIGHT(1, 0, DirectionCheckerFactory::isRight),
    DOWN_RIGHT(1, -1, DirectionCheckerFactory::isDownRight),
    DOWN(0, -1, DirectionCheckerFactory::isDown),
    DOWN_LEFT(-1, -1, DirectionCheckerFactory::isDownLeft),
    LEFT(-1, 0, DirectionCheckerFactory::isLeft),
    UP_LEFT(-1, 1, DirectionCheckerFactory::isUpLeft),
    UP_UP_RIGHT(1, 2, DirectionCheckerFactory::isUpUpRight),
    UP_UP_LEFT(-1, 2, DirectionCheckerFactory::isUpUpLeft),
    RIGHT_RIGHT_UP(2, 1, DirectionCheckerFactory::isRightRightUp),
    RIGHT_RIGHT_DOWN(2, -1, DirectionCheckerFactory::isRightRightDown),
    DOWN_DOWN_RIGHT(1, -2, DirectionCheckerFactory::isDownDownRight),
    DOWN_DOWN_LEFT(-1, -2, DirectionCheckerFactory::isDownDownLeft),
    LEFT_LEFT_UP(-2, 1, DirectionCheckerFactory::isLeftLeftUp),
    LEFT_LEFT_DOWN(-2, -1, DirectionCheckerFactory::isLeftLeftDown);

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
