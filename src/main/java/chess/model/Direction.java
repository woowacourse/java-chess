package chess.model;

import java.util.List;

public enum Direction {
    NORTH, SOUTH, WEST, EAST, NORTHWEST, NORTHEAST, SOUTHWEST, SOUTHEAST,
    KNIGHT_NORTHWEST, KNIGHT_NORTHEAST, KNIGHT_EASTNORTH, KNIGHT_EASTSOUTH,
    KNIGHT_SOUTHWEST, KNIGHT_SOUTHEAST, KNIGHT_WESTNORTH, KNIGHT_WESTSOUTH;

    public static Direction findDirection(List<Coordinate> coordinates) {
        Coordinate sourceCoordinateX = coordinates.get(0);
        Coordinate sourceCoordinateY = coordinates.get(1);
        Coordinate targetCoordinateX = coordinates.get(2);
        Coordinate targetCoordinateY = coordinates.get(3);

        if ((targetCoordinateX.calculateDistance(sourceCoordinateX) == -1)
                && targetCoordinateY.calculateDistance(sourceCoordinateY) == 2) {
            return KNIGHT_NORTHWEST;
        }

        if ((targetCoordinateX.calculateDistance(sourceCoordinateX) == 1)
                && targetCoordinateY.calculateDistance(sourceCoordinateY) == 2) {
            return KNIGHT_NORTHEAST;
        }

        if ((targetCoordinateX.calculateDistance(sourceCoordinateX) == 2)
                && targetCoordinateY.calculateDistance(sourceCoordinateY) == 1) {
            return KNIGHT_EASTNORTH;
        }

        if ((targetCoordinateX.calculateDistance(sourceCoordinateX) == 2)
                && targetCoordinateY.calculateDistance(sourceCoordinateY) == -1) {
            return KNIGHT_EASTSOUTH;
        }

        if ((targetCoordinateX.calculateDistance(sourceCoordinateX) == 1)
                && targetCoordinateY.calculateDistance(sourceCoordinateY) == -2) {
            return KNIGHT_SOUTHEAST;
        }

        if ((targetCoordinateX.calculateDistance(sourceCoordinateX) == -1)
                && targetCoordinateY.calculateDistance(sourceCoordinateY) == -2) {
            return KNIGHT_SOUTHWEST;
        }

        if ((targetCoordinateX.calculateDistance(sourceCoordinateX) == -2)
                && targetCoordinateY.calculateDistance(sourceCoordinateY) == -1) {
            return KNIGHT_WESTSOUTH;
        }

        if ((targetCoordinateX.calculateDistance(sourceCoordinateX) == -2)
                && targetCoordinateY.calculateDistance(sourceCoordinateY) == 1) {
            return KNIGHT_WESTNORTH;
        }

        if ((sourceCoordinateX == targetCoordinateX) && (targetCoordinateY.calculateDistance(sourceCoordinateY) > 0)) {
            return NORTH;
        }

        if ((sourceCoordinateX == targetCoordinateX) && (targetCoordinateY.calculateDistance(sourceCoordinateY) < 0)) {
            return SOUTH;
        }

        if ((sourceCoordinateY == targetCoordinateY) && (targetCoordinateX.calculateDistance(sourceCoordinateX) < 0)) {
            return WEST;
        }

        if ((sourceCoordinateY == targetCoordinateY) && (targetCoordinateX.calculateDistance(sourceCoordinateX)) > 0) {
            return EAST;
        }

        if ((targetCoordinateX.calculateDistance(sourceCoordinateX) < 0)
                && targetCoordinateY.calculateDistance(sourceCoordinateY) > 0) {
            return NORTHWEST;
        }

        if ((targetCoordinateX.calculateDistance(sourceCoordinateX) > 0)
                && targetCoordinateY.calculateDistance(sourceCoordinateY) > 0) {
            return NORTHEAST;
        }

        if ((targetCoordinateX.calculateDistance(sourceCoordinateX) < 0)
                && targetCoordinateY.calculateDistance(sourceCoordinateY) < 0) {
            return SOUTHWEST;
        }

        if ((targetCoordinateX.calculateDistance(sourceCoordinateX) > 0)
                && targetCoordinateY.calculateDistance(sourceCoordinateY) < 0) {
            return SOUTHEAST;
        }

        return null;

    }
}
