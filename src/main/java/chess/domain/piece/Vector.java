package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

public enum Vector {
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

    private final int x;
    private final int y;

    Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static List<Vector> pawnVectors() {
        return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
    }

    public static List<Vector> everyVectors() {
        return Arrays.asList(EAST, WEST, SOUTH, NORTH, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
    }

    public static List<Vector> diagonalVectors() {
        return Arrays.asList(NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
    }

    public static List<Vector> axisVectors() {
        return Arrays.asList(EAST, WEST, SOUTH, NORTH);
    }

    public static List<Vector> knightVectors() {
        return Arrays.asList(NNE, NEE, SEE, SSE, SSW, SWW, NWW, NNW);
    }

    public static Vector get(int horizon, int vertical) {
        return Arrays.stream(Vector.values())
            .filter(vector -> (vector.x == horizon)
                && (vector.y == vertical))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isWhitePawnsStraight() {
        return this == NORTH;
    }

    public boolean isBlackPawnsStraight() {
        return this == SOUTH;
    }

    public boolean isWhiteDiagonalVector() {
        return this == NORTHEAST || this == NORTHWEST;
    }

    public boolean isBlackDiagonalVector() {
        return this == SOUTHEAST || this == SOUTHWEST;
    }

    public boolean isSameDirection(int x, int y) {
        if (isOppositeDirection(x, y)) {
            return false;
        }
        return this.x * y == this.y * x;
    }

    private boolean isOppositeDirection(int x, int y) {
        return (this.x * x < 0) || (this.y * y < 0);
    }

    public Vector opposite() {
        return get((-1) * x, (-1) * y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
