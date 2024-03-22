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

    private static final int MAX_BOARD_DISTANCE = 8;
    private static final int MIN_BOARD_DISTANCE = 1;

    private final int fileVector;
    private final int rankVector;

    Direction(final int fileVector, final int rankVector) {
        this.fileVector = fileVector;
        this.rankVector = rankVector;
    }


    public static Direction findDirection(Position source, Position target) {
        Vector vector = target.subtract(source);
        return Arrays.stream(values())
                .filter(direction -> isSameDirection(direction, vector))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("움직일 수 없는 방향입니다."));
    }

    private static boolean isSameDirection(final Direction direction, final Vector vector) {
        for (int step = MIN_BOARD_DISTANCE; step <= MAX_BOARD_DISTANCE; step++) {
            if (canReach(findDistance(direction.fileVector, step), vector.file()) && canReach(
                    findDistance(direction.rankVector, step), vector.rank())) {
                return true;
            }
        }
        return false;
    }

    private static int findDistance(int unitVector, int step) {
        return unitVector * step;
    }

    private static boolean canReach(int step, int unitVector) {
        return step == unitVector;
    }

    public int getFileVector() {
        return fileVector;
    }

    public int getRankVector() {
        return rankVector;
    }
}
