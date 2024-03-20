package domain.movement;

import java.util.Arrays;
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
        for (Direction direction : KNIGHT) {
            if (direction.rankDiff == rankDiff && direction.fileDiff == fileDiff) {
                return direction;
            }
        }

        int maxDiff = Math.max(Math.abs(fileDiff), Math.abs(rankDiff));

        return Arrays.stream(values())
                .filter(direction -> !KNIGHT.contains(direction))
                .filter(direction -> direction.fileDiff * maxDiff == fileDiff
                        && direction.rankDiff * maxDiff == rankDiff)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 방향입니다."));
    }

    public int fileDiff() {
        return fileDiff;
    }

    public int rankDiff() {
        return rankDiff;
    }
}
