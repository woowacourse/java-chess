package domain;

import domain.piece.Position;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    EAST(0, 1),
    SOUTHEAST(1, 1),
    SOUTH(1, 0),
    SOUTHWEST(1, -1),
    WEST(0, -1),
    NORTHWEST(-1, -1),
    NORTH(-1, 0),
    NORTHEAST(-1, 1),

    NNE(1, 2),
    NNW(-1, 2),
    SSE(1, -2),
    SSW(-1, -2),
    EEN(2, 1),
    EES(2, -1),
    WWN(-2, 1),
    WWS(-2, -1);

    private int x;
    private int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Direction findDiagonalDirection(Position start, Position end) { // 4개의 대각선 중 방향을 체크한다.
        int row = end.getRow() - start.getRow();
        int col = end.getColumn() - start.getColumn();

        if (row > 0 && col > 0) {
            return SOUTHEAST;
        }

        if (row > 0 && col < 0) {
            return SOUTHWEST;
        }

        if (row < 0 && col < 0) {
            return NORTHWEST;
        }

        return NORTHEAST;
    }

    public static Direction findLinearDirection(Position start, Position end) {
        int rowDiff = end.getRow() - start.getRow();
        int colDiff = end.getColumn() - start.getColumn();

        if (rowDiff < 0) {
            return NORTH;
        }

        if (rowDiff > 0) {
            return SOUTH;
        }

        if (colDiff < 0) {
            return WEST;
        }

        return EAST;
    }

    public static List<Direction> linearDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST);
    }

    public static List<Direction> diagonalDirection() {
        return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> knightDirection() {
        return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    }

    public static List<Direction> blackPawnDirection() {
        return Arrays.asList(SOUTH, SOUTHWEST, SOUTHEAST);
    }

    public static List<Direction> whitePawnDirection() {
        return Arrays.asList(NORTH, NORTHWEST, NORTHEAST);
    }
}
