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

    private final int horizontal;
    private final int vertical;

    MoveVector(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
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

    public static List<MoveVector> pawnVectors() {
        return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
    }

    public static List<MoveVector> knightVectors() {
        return Arrays.asList(NNE, NEE, SEE, SSE, SSW, SWW, NWW, NNW);
    }

    public static MoveVector moveVectorByValues(int horizontal, int vertical) {
        return Arrays.stream(MoveVector.values())
            .filter(vector -> (vector.horizontal == horizontal)
                && (vector.vertical == vertical))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isDiagonalVector() {
        return diagonalVectors().contains(this);
    }

    public boolean isPawnStraight() {
        return this == NORTH || this == SOUTH;
    }

    public boolean isSameDirection(int horizontal, int vertical) {
        if (isOppositeDirection(horizontal, vertical)) {
            return false;
        }
        return this.horizontal * vertical == this.vertical * horizontal;
    }

    private boolean isOppositeDirection(int horizontal, int vertical) {
        return (this.horizontal * horizontal < 0) || (this.vertical * vertical < 0);
    }

    public MoveVector oppositeVector() {
        return moveVectorByValues((-1) * horizontal, (-1) * vertical);
    }

    public int horizontal() {
        return horizontal;
    }

    public int vertical() {
        return vertical;
    }
}
