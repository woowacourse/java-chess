package domain.movement;

import java.util.List;

public enum Direction {
    UP(1, 0),
    DOWN(-1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    UP_LEFT(1, -1),
    UP_RIGHT(1, 1),
    DOWN_LEFT(-1, -1),
    DOWN_RIGHT(-1, 1),
    KNIGHT_UP_LEFT(2, -1),
    KNIGHT_UP_RIGHT(2, 1),
    KNIGHT_RIGHT_UP(1, 2),
    KNIGHT_RIGHT_DOWN(-1, 2),
    KNIGHT_DOWN_RIGHT(-2, 1),
    KNIGHT_DOWN_LEFT(-2, -1),
    KNIGHT_LEFT_DOWN(-1, -2),
    KNIGHT_LEFT_UP(1, -2);

    private static final List<Direction> KNIGHT = List.of(KNIGHT_UP_LEFT, KNIGHT_UP_RIGHT, KNIGHT_RIGHT_UP,
            KNIGHT_RIGHT_DOWN,
            KNIGHT_DOWN_RIGHT, KNIGHT_DOWN_LEFT, KNIGHT_LEFT_DOWN, KNIGHT_LEFT_UP);
    private final int rankDiff;
    private final int fileDiff;

    Direction(int rankDiff, int fileDiff) {
        this.rankDiff = rankDiff;
        this.fileDiff = fileDiff;
    }

    public static Direction of(int rankDiff, int fileDiff) {
        validateNoDifference(rankDiff, fileDiff);

        for (Direction direction : KNIGHT) {
            if (direction.rankDiff == rankDiff && direction.fileDiff == fileDiff) {
                return direction;
            }
        }

        int count = Math.max(Math.abs(fileDiff), Math.abs(rankDiff));

        validateInvalidDirection(rankDiff, fileDiff, count);

        fileDiff /= count;
        rankDiff /= count;

        for (Direction direction : values()) {
            if (direction.fileDiff == fileDiff && direction.rankDiff == rankDiff) {
                return direction;
            }
        }

        throw new IllegalArgumentException("올바르지 않은 방향입니다.");
    }

    private static void validateNoDifference(int rankDiff, int fileDiff) {
        if (fileDiff == 0 && rankDiff == 0) {
            throw new IllegalArgumentException("동일한 위치입니다.");
        }
    }

    private static void validateInvalidDirection(int rankDiff, int fileDiff, int count) {
        if (fileDiff % count != 0 || rankDiff % count != 0) {
            throw new IllegalArgumentException("올바르지 않은 방향입니다.");
        }
    }
}
