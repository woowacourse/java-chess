package chess.domain;

import chess.exception.IllegalMoveException;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH(0, 1),
    WEST(-1, 0),
    EAST(1, 0),
    SOUTH(0, -1),

    NORTHNORTH(0, 2),
    SOUTHSOUTH(0, -2),

    NORTHEAST(1, 1),
    NORTHWEST(-1, 1),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),

    NNE(1, 2),
    NEE(2, 1),
    NNW(-1, 2),
    NWW(-2, 1),
    SSE(1, -2),
    SEE(2, -1),
    SSW(-1, -2),
    SWW(-2, -1);

    private static final String INVALID_DIRECTION = "올바르지 않은 방향입니다.";
    private int xDegree;
    private int yDegree;

    Direction(int xDegree, int yDegree) {
        this.xDegree = xDegree;
        this.yDegree = yDegree;
    }

    public static Direction of(int x, int y) {
        return Arrays.stream(Direction.values())
                .filter(d -> d.xDegree == x && d.yDegree == y)
                .findFirst()
                .orElseThrow(() -> new IllegalMoveException(INVALID_DIRECTION));
    }

    public boolean isDiagonal() {
        List<Direction> diagonalDirection = Arrays.asList(NORTHWEST, NORTHEAST, SOUTHWEST, SOUTHEAST);
        return diagonalDirection.contains(this);
    }

    public boolean isForwardDouble() {
        return (this == NORTHNORTH || this == SOUTHSOUTH);
    }

    public boolean isForwardForPawn() {
        List<Direction> forwardDirection = Arrays.asList(NORTH, NORTHNORTH, SOUTH, SOUTHSOUTH);
        return forwardDirection.contains(this);
    }

    public int getYDegree() {
        return yDegree;
    }

    public int getXDegree() {
        return xDegree;
    }
}
