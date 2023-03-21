package techcourse.fp.chess.movingStrategy;

import java.util.Arrays;

public enum KnightMove {
    UP_TWO_LEFT(-1, 2),
    UP_TWO_RIGHT(1, 2),
    DOWN_TWO_LEFT(-1, -2),
    DOWN_TWO_RIGHT(1, -2),
    RIGHT_TWO_UP(2, 1),
    RIGHT_TWO_DOWN(2, -1),
    LEFT_TWO_UP(-2, 1),
    LEFT_TWO_DOWN(-2, -1);

    private final int x;
    private final int y;

    KnightMove(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean isMovable(final int targetX, final int targetY) {
        return Arrays.stream(KnightMove.values())
                .anyMatch(
                        knightVector -> knightVector.x == targetX && knightVector.y == targetY);
    }
}
