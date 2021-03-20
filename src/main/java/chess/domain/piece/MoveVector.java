package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

public enum MoveVector {
    EAST(1, 0),
    WEST(-1, 0),
    SOUTH(0, -1),
    NORTH(0, 1),
    NORTHEAST(1, 1),
    NORTHWEST(-1, 1),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),

    NNE(1, 2),
    NEE(2, 1),
    SEE(2, -1),
    SSE(1, -2),
    SSW(-1, -2),
    SWW(-2, -1),
    NWW(-2, 1),
    NNW(-1, 2);

    private final int horizon;
    private final int vertical;

    MoveVector(int horizon, int vertical) {
        this.horizon = horizon;
        this.vertical = vertical;
    }

    public static List<MoveVector> pawnVectors() {
        return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
    }

    public static List<MoveVector> everyVectors() {
        return Arrays.asList(EAST, WEST, SOUTH, NORTH, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
    }

    public static List<MoveVector> diagonalVectors() {
        return Arrays.asList(NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
    }

    public static List<MoveVector> axisVectors() {
        return Arrays.asList(EAST, WEST, SOUTH, NORTH);
    }

    public static List<MoveVector> knightVectors() {
        return Arrays.asList(NNE, NEE, SEE, SSE, SSW, SWW, NWW, NNW);
    }

    public boolean isDiagonalVector() {
        return diagonalVectors().contains(this);
    }

    public boolean isPawnStraight() {
        return this == NORTH || this == SOUTH;
    }

    public static MoveVector foundMoveVector(int horizon, int vertical) {
        return Arrays.stream(MoveVector.values())
            .filter(vector -> (vector.horizon == horizon)
                && (vector.vertical == vertical))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isSameDirection(int horizon, int vertical) {
        if (isOppositeDirection(horizon, vertical)) {
            return false;
        }
        return this.horizon * vertical == this.vertical * horizon;
    }

    private boolean isOppositeDirection(int horizon, int vertical) {
        return (this.horizon * horizon < 0) || (this.vertical * vertical < 0);
    }

    public MoveVector oppositeVector() {
        return foundMoveVector((-1) * horizon, (-1) * vertical);
    }

    public int getHorizon() {
        return horizon;
    }

    public int getVertical() {
        return vertical;
    }
}
