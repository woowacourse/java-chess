package chess.domain.piece.direction;

import java.util.Arrays;

public enum Direction {
    NORTH(0, 1),
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

    private int xPoint;
    private int yPoint;

    Direction(int xPoint, int yPoint) {
        this.xPoint = xPoint;
        this.yPoint = yPoint;
    }

    public static Direction of(int xPointDirectionValue, int yPointDirectionValue) {
        return Arrays.stream(values())
                .filter(value -> value.isEqualPoint(xPointDirectionValue, yPointDirectionValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 방향입니다."));
    }

    private boolean isEqualPoint(int xPointDirectionValue, int row) {
        return xPoint == xPointDirectionValue && yPoint == row;
    }

    public boolean isSouth() {
        return this == SOUTH;
    }

    public boolean isNorth() {
        return this == NORTH;
    }

    public int getXPointValue() {
        return xPoint;
    }

    public int getYPointValue() {
        return yPoint;
    }
}
