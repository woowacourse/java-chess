package chess.model.board.vector;

import chess.model.board.Coordinate;

import java.util.List;

public enum Direction {
    NORTH(0, 1), SOUTH(0, -1), WEST(-1, 0), EAST(1, 0),
    NORTHWEST(-1, 1), NORTHEAST(1, 1), SOUTHWEST(-1, -1), SOUTHEAST(1, -1),
    KNIGHT_NORTHWEST(-1, 2), KNIGHT_NORTHEAST(1, 2),
    KNIGHT_EASTNORTH(2, 1), KNIGHT_EASTSOUTH(2, -1),
    KNIGHT_SOUTHWEST(-1, -2), KNIGHT_SOUTHEAST(1, -2),
    KNIGHT_WESTNORTH(-2, 1), KNIGHT_WESTSOUTH(-2, -1),
    FAULT_DIRECTION(0, 0);

    private int unitX;
    private int unitY;

    Direction(int unitX, int unitY) {
        this.unitX = unitX;
        this.unitY = unitY;
    }

    public static Direction findDirection(List<Coordinate> coordinates) {
        Coordinate sourceCoordinateX = coordinates.get(0);
        Coordinate sourceCoordinateY = coordinates.get(1);
        Coordinate targetCoordinateX = coordinates.get(2);
        Coordinate targetCoordinateY = coordinates.get(3);

        int distanceX = targetCoordinateX.calculateDistance(sourceCoordinateX);
        int distanceY = targetCoordinateY.calculateDistance(sourceCoordinateY);

        if ((distanceX == -1) && (distanceY == 2)) {
            return KNIGHT_NORTHWEST;
        }

        if ((distanceX == 1) && (distanceY == 2)) {
            return KNIGHT_NORTHEAST;
        }

        if ((distanceX == 2) && (distanceY == 1)) {
            return KNIGHT_EASTNORTH;
        }

        if ((distanceX == 2) && (distanceY == -1)) {
            return KNIGHT_EASTSOUTH;
        }

        if ((distanceX == 1) && (distanceY == -2)) {
            return KNIGHT_SOUTHEAST;
        }

        if ((distanceX == -1) && (distanceY == -2)) {
            return KNIGHT_SOUTHWEST;
        }

        if ((distanceX == -2) && (distanceY == -1)) {
            return KNIGHT_WESTSOUTH;
        }

        if ((distanceX == -2) && (distanceY == 1)) {
            return KNIGHT_WESTNORTH;
        }

        if ((sourceCoordinateX == targetCoordinateX) && (distanceY > 0)) {
            return NORTH;
        }

        if ((sourceCoordinateX == targetCoordinateX) && (distanceY < 0)) {
            return SOUTH;
        }

        if ((sourceCoordinateY == targetCoordinateY) && (distanceX < 0)) {
            return WEST;
        }

        if ((sourceCoordinateY == targetCoordinateY) && (distanceX) > 0) {
            return EAST;
        }

        if ((distanceX < 0) && (distanceY > 0) && (Math.abs(distanceX) == Math.abs(distanceY))) {
            return NORTHWEST;
        }

        if ((distanceX > 0) && (distanceY > 0) && (Math.abs(distanceX) == Math.abs(distanceY))) {
            return NORTHEAST;
        }

        if ((distanceX < 0) && (distanceY < 0) && (Math.abs(distanceX) == Math.abs(distanceY))) {
            return SOUTHWEST;
        }

        if ((distanceX > 0) && (distanceY < 0) && (Math.abs(distanceX) == Math.abs(distanceY))) {
            return SOUTHEAST;
        }

        return FAULT_DIRECTION;

    }

    public static boolean isVertical(Direction direction) {
        return (direction == NORTH) || (direction == SOUTH);
    }

    public static boolean isHorizontal(Direction direction) {
        return (direction == EAST) || (direction == WEST);
    }

    public static boolean isDiagonal(Direction direction) {
        return (direction == NORTHEAST) || (direction == NORTHWEST)
                || (direction == SOUTHEAST) || (direction == SOUTHWEST);
    }

    public int getUnitX() {
        return unitX;
    }

    public int getUnitY() {
        return unitY;
    }
}
