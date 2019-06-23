package chess.model;

import java.util.List;

public enum Direction {
    NORTH, SOUTH, WEST, EAST, NORTHWEST, NORTHEAST, SOUTHWEST, SOUTHEAST,
    KNIGHT_NORTHWEST, KNIGHT_NORTHEAST, KNIGHT_EASTNORTH, KNIGHT_EASTSOUTH,
    KNIGHT_SOUTHWEST, KNIGHT_SOUTHEAST, KNIGHT_WESTNORTH, KNIGHT_WESTSOUTH,
    FAULT_DIRECTION;

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

    public static boolean isDiagonal(Direction direction) {
        return (direction == NORTHEAST) || (direction == NORTHWEST)
                || (direction == SOUTHEAST) || (direction == SOUTHWEST);
    }
}
