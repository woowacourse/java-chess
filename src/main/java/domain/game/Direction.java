package domain.game;

import domain.position.Position;
import java.util.Arrays;

public enum Direction {

    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1),

    // KNIGHT DIRECTION
    UP_RIGHT(1, 2),
    UP_LEFT(-1, 2),
    RIGHT_UP(2, 1),
    RIGHT_DOWN(2, -1),
    LEFT_DOWN(-2, -1),
    LEFT_UP(-2, 1),
    DOWN_RIGHT(1, -2),
    DOWN_LEFT(-1, -2);

    private final int fileUnit;
    private final int rankUnit;

    Direction(final int fileUnit, final int rankUnit) {
        this.fileUnit = fileUnit;
        this.rankUnit = rankUnit;
    }


    public static Direction findDirection(Position sourcePosition, Position targetPosition) {
        Gap gap = targetPosition.subtract(sourcePosition);
        return Arrays.stream(values())
                .filter(direction -> direction.isSameDirection(direction, gap))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("움직일 수 없는 방향입니다."));
    }

    private static boolean isSameDirection(final Direction direction, final Gap gap) {
        for (int i = 1; i <= 8; i++) {
            if (direction.fileUnit * i == gap.fileGap() && direction.rankUnit * i == gap.rankGap()) {
                return true;
            }
        }
        return false;
    }

    public int getFileUnit() {
        return fileUnit;
    }

    public int getRankUnit() {
        return rankUnit;
    }
}
