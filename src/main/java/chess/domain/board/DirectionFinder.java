package chess.domain.board;

import chess.domain.location.Location;
import java.util.ArrayList;
import java.util.List;

public class DirectionFinder {

    private static final int POSITIVE_DISTANCE = 1;
    private static final int NEUTRAL_DISTANCE = 0;
    private static final int NEGATIVE_DISTANCE = -1;

    public static List<Direction> createDirections(Location source, Location target) {
        int verticalDistance = source.calculateVerticalDistance(target);
        int horizontalDistance = source.calculateHorizontalDistance(target);
        validateDistance(verticalDistance, horizontalDistance);
        return createDirectionsByDistance(verticalDistance, horizontalDistance);
    }

    private static List<Direction> createDirectionsByDistance(int verticalDistance, int horizontalDistance) {
        List<Direction> directions = new ArrayList<>();
        int verticalDistanceCheck = verticalDistance;
        int horizontalDistanceCheck = horizontalDistance;
        while (verticalDistanceCheck != NEUTRAL_DISTANCE || horizontalDistanceCheck != NEUTRAL_DISTANCE) {
            Direction direction = Direction.of(verticalDistanceCheck, horizontalDistanceCheck);
            directions.add(direction);
            verticalDistanceCheck -= calculateVerticalDistance(direction);
            horizontalDistanceCheck -= calculateHorizontalDistance(direction);
        }
        return directions;
    }

    private static int calculateVerticalDistance(Direction direction) {
        if (direction.isUpside()) {
            return POSITIVE_DISTANCE;
        }
        if (direction.isDownside()) {
            return NEGATIVE_DISTANCE;
        }
        return NEUTRAL_DISTANCE;
    }

    private static int calculateHorizontalDistance(Direction direction) {
        if (direction.isRightSide()) {
            return POSITIVE_DISTANCE;
        }
        if (direction.isLeftSide()) {
            return NEGATIVE_DISTANCE;
        }
        return NEUTRAL_DISTANCE;
    }

    private static void validateDistance(int verticalDistance, int horizontalDistance) {
        if (verticalDistance == NEUTRAL_DISTANCE && horizontalDistance == NEUTRAL_DISTANCE) {
            throw new IllegalArgumentException("제자리 경로를 생성할 수 없습니다.");
        }
    }

}
