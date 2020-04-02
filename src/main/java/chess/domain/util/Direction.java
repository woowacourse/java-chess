package chess.domain.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH(0, 1),
    SOUTH(0, -1),
    EAST(1, 0),
    WEST(-1, 0),
    NORTH_NORTH(0, 2),
    SOUTH_SOUTH(0, -2),
    NORTH_EAST(1, 1),
    NORTH_WEST(-1, 1),
    SOUTH_EAST(1, -1),
    SOUTH_WEST(-1, -1),
    SOUTH_SOUTH_EAST(1, -2),
    SOUTH_SOUTH_WEST(-1, -2),
    NORTH_NORTH_EAST(1, 2),
    NORTH_NORTH_WEST(-1, 2),
    EAST_EAST_SOUTH(2, -1),
    EAST_EAST_NORTH(2, 1),
    WEST_WEST_SOUTH(-2, -1),
    WEST_WEST_NORTH(-2, 1);

    private final int col;
    private final int row;

    Direction(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int getColumn() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public static List<Direction> getDirectionsOfFirstWhitePawn() {
        return new ArrayList<>(
                Arrays.asList(NORTH, NORTH_NORTH, NORTH_EAST, NORTH_WEST)
        );
    }

    public static List<Direction> getDirectionsOfWhitePawn() {
        return new ArrayList<>(
                Arrays.asList(NORTH, NORTH_EAST, NORTH_WEST)
        );
    }

    public static List<Direction> getDirectionsOfFirstBlackPawn() {
        return new ArrayList<>(
                Arrays.asList(SOUTH, SOUTH_SOUTH, SOUTH_EAST, SOUTH_WEST)
        );
    }

    public static List<Direction> getDirectionsOfBlackPawn() {
        return new ArrayList<>(
                Arrays.asList(SOUTH, SOUTH_EAST, SOUTH_WEST)
        );
    }

    public static List<Direction> getDirectionsOfRook() {
        return new ArrayList<>(
                Arrays.asList(NORTH, EAST, SOUTH, WEST)
        );
    }

    public static List<Direction> getDirectionsOfKnight() {
        return new ArrayList<>(
                Arrays.asList(NORTH_NORTH_EAST, EAST_EAST_NORTH, EAST_EAST_SOUTH, SOUTH_SOUTH_EAST,
                        SOUTH_SOUTH_WEST, WEST_WEST_SOUTH, WEST_WEST_NORTH, NORTH_NORTH_WEST)
        );
    }

    public static List<Direction> getDirectionsOfBishop() {
        return new ArrayList<>(
                Arrays.asList(NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST)
        );
    }

    public static List<Direction> getDirectionsOfQueen() {
        return new ArrayList<>(
                Arrays.asList(NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST)
        );
    }

    public static List<Direction> getDirectionsOfKing() {
        return new ArrayList<>(
                Arrays.asList(NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST)
        );
    }


    public boolean isForwardDirection() {
        return Arrays.asList(NORTH, NORTH_NORTH, SOUTH, SOUTH_SOUTH).contains(this);
    }
}
