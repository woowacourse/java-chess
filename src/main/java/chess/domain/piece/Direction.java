package chess.domain.piece;

import chess.domain.position.Difference;
import java.util.Arrays;

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

    NNE(1, 2),
    NNW(-1, 2),
    SSE(1, -2),
    SSW(-1, -2),
    EEN(2, 1),
    EES(2, -1),
    WWN(-2, 1),
    WWS(-2, -1);

    private final int horizontalDegree;
    private final int verticalDegree;

    Direction(final int horizontalDegree, final int verticalDegree) {
        this.horizontalDegree = horizontalDegree;
        this.verticalDegree = verticalDegree;
    }

    public static Direction matchedDirection(final int horizontalDegree, final int verticalDegree) {
        return Arrays.stream(values())
            .filter(direction -> direction.horizontalDegree == horizontalDegree)
            .filter(direction -> direction.verticalDegree == verticalDegree)
            .findFirst()
            .orElse(NOTHING);
    }

    public boolean isSameDirection(final Difference difference) {
        return this == matchedDirection(difference.horizontalDegree(), difference.verticalDegree());
    }

    public int horizontalDegree() {
        return horizontalDegree;
    }

    public int verticalDegree() {
        return verticalDegree;
    }

    public boolean isNorth() {
        return this == NORTH;
    }

    public boolean isNorthWest() {
        return this == NORTHWEST;
    }

    public boolean isNorthEast() {
        return this == NORTHEAST;
    }

    public boolean isInitialPawnNorth() {
        return this == INITIAL_PAWN_NORTH;
    }
}
