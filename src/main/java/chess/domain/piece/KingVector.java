package chess.domain.piece;

import java.util.Arrays;

public enum KingVector {

    NORTH(0, 1),
    SOUTH(0, -1),
    WEST(-1, 0),
    EAST(1, 0),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),
    NORTHEAST(1, 1),
    NORTHWEST(-1, 1);

    private final int x;
    private final int y;

    KingVector(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean isExistMovableVector(final int distanceX, final int distanceY) {
        return Arrays.stream(KingVector.values())
                .anyMatch(kingVector -> kingVector.x == distanceX && kingVector.y == distanceY);
    }
}
