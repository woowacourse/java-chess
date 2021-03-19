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

    FIRST_PAWN_UP(0, 2),
    FIRST_PAWN_DOWN(0, -2),

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
        return Arrays.asList(NORTH, FIRST_PAWN_UP);
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

    public static boolean hasNotMoveVector(int horizon, int vertical) {
        return Arrays.stream(MoveVector.values())
            .noneMatch(vector -> (vector.horizon == horizon)
                && (vector.vertical == vertical));
    }

    public static MoveVector foundMoveVector(int horizon, int vertical) {
        return Arrays.stream(MoveVector.values())
            .filter(vector -> (vector.horizon == horizon)
                && (vector.vertical == vertical))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isWhitePawnsStraight() {
        return Arrays.asList(NORTH, FIRST_PAWN_UP).contains(this);
    }

    public boolean isBlackPawnsStraight() {
        return Arrays.asList(SOUTH, FIRST_PAWN_DOWN).contains(this);
    }

    public boolean isWhiteDiagonalVector() {
        return this == NORTHEAST || this == NORTHWEST;
    }

    public boolean isBlackDiagonalVector() {
        return this == SOUTHEAST || this == SOUTHWEST;
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
