package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

public enum Direction {

    NOTHING(0, 0),
    NORTH(0, 1),
    INITIAL_PAWN_NORTH(0, 2),
    NORTHEAST(1, 1),
    EAST(1, 0),
    SOUTHEAST(1, -1),
    SOUTH(0, -1),
    SOUTHWEST(-1, -1),
    WEST(-1, 0),
    NORTHWEST(-1, 1),

    NORTH_NORTHEAST(1, 2),
    NORTH_NORTHWEST(-1, 2),
    SOUTH_SOUTHEAST(1, -2),
    SOUTH_SOUTHWEST(-1, -2),
    EAST_NORTHEAST(2, 1),
    EAST_SOUTHEAST(2, -1),
    WEST_NORTHWEST(-2, 1),
    WEST_SOUTHWEST(-2, -1);

    private final int horizontalDegree;
    private final int verticalDegree;

    Direction(final int horizontalDegree, final int verticalDegree) {
        this.horizontalDegree = horizontalDegree;
        this.verticalDegree = verticalDegree;
    }

    public static Direction matchingDirection(final List<Integer> result) {
        return Arrays.stream(values())
                .filter(i -> i.horizontalDegree == result.get(0))
                .filter(i -> i.verticalDegree == result.get(1))
                .findFirst()
                .orElse(NOTHING);
    }

    public boolean isSameDirection(final List<Integer> result) {
        return this == matchingDirection(result);
    }

    public int horizontalDegree() {
        return horizontalDegree;
    }

    public int verticalDegree() {
        return verticalDegree;
    }
}
