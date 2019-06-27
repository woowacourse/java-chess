package chess.domain;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    LEFT_TOP(-1, 1),
    TOP(0, 1),
    RIGHT_TOP(1, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    LEFT_BOTTOM(-1, -1),
    BOTTOM(0, -1),
    RIGHT_BOTTOM(1, -1),

    LEFT_LEFT_TOP(-2, 1),
    LEFT_LEFT_BOTTOM(-2, -1),
    TOP_TOP_LEFT(-1, 2),
    TOP_TOP_RIGHT(1, 2),
    RIGHT_RIGHT_TOP(2, 1),
    RIGHT_RIGHT_BOTTOM(2, -1),
    BOTTOM_BOTTOM_RIGHT(1, -2),
    BOTTOM_BOTTOM_LEFT(-1, -2);

    private int directionX;
    private int directionY;

    Direction(int directionX, int directionY) {
        this.directionX = directionX;
        this.directionY = directionY;
    }

    public int getDirectionX() {
        return directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    public static List<Direction> linearDirection() {
        return Arrays.asList(TOP, RIGHT, BOTTOM, LEFT);
    }

//    public static List<Direction> diagonalDirection() {
//        return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
//    }
//
//    public static List<Direction> everyDirection() {
//        return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
//    }
//
//    public static List<Direction> knightDirection() {
//        return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
//    }
//
//    public static List<Direction> whitePawnDirection() {
//        return Arrays.asList(NORTH, NORTHTWO, NORTHEAST, NORTHWEST);
//    }
//
//    public static List<Direction> blackPawnDirection() {
//        return Arrays.asList(SOUTH, SOUTHTWO, SOUTHEAST, SOUTHWEST);
//    }
}
