package chess.domain.piece;

import java.util.Arrays;

public enum KnightVector {

    TWO_NORTH_ONE_WEST(-1, 2),
    TWO_NORTH_ONE_EAST(1, 2),
    TWO_EAST_ONE_NORTH(2, 1),
    TWO_EAST_ONE_SOUTH(2, -1),
    TWO_SOUTH_ONE_EAST(1, -2),
    TWO_SOUTH_ONE_WEST(-1, -2),
    TWO_WEST_ONE_NORTH(-2, 1),
    TWO_WEST_ONE_SOUTH(-2, -1);

    private final int x;
    private final int y;

    KnightVector(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean isExistMovableVector(final int distanceX, final int distanceY) {
        return Arrays.stream(KnightVector.values())
                .anyMatch(knightVector -> knightVector.x == distanceX && knightVector.y == distanceY);
    }
}
