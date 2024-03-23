package chess.domain.board;

import chess.domain.location.Location;
import java.util.ArrayList;
import java.util.List;

public enum Direction {
    UP(1, 0),
    UP_RIGHT(1, 1),
    RIGHT(0, 1),
    DOWN_RIGHT(-1, 1),
    DOWN(-1, 0),
    DOWN_LEFT(-1, -1),
    LEFT(0, -1),
    UP_LEFT(1, -1);

    private final int rowDistance;
    private final int columnDistance;


    Direction(int rowDistance, int columnDistance) {
        this.rowDistance = rowDistance;
        this.columnDistance = columnDistance;
    }

    private static Direction of(int rowDistance, int columnDistance) {
        if (rowDistance > 0) {
            return createUpsideDirection(columnDistance);
        }
        if (rowDistance < 0) {
            return createDownsideDirection(columnDistance);
        }
        return createColumnDirection(columnDistance);
    }

    private static Direction createUpsideDirection(int columnDistance) {
        if (columnDistance < 0) {
            return UP_LEFT;
        }
        if (columnDistance == 0) {
            return UP;
        }
        return UP_RIGHT;
    }

    private static Direction createDownsideDirection(int columnDistance) {
        if (columnDistance < 0) {
            return DOWN_LEFT;
        }
        if (columnDistance == 0) {
            return DOWN;
        }
        return DOWN_RIGHT;
    }

    private static Direction createColumnDirection(int columnDistance) {
        if (columnDistance < 0) {
            return LEFT;
        }
        return RIGHT;
    }

    public static List<Direction> createDirections(Location source, Location target) {
        int rowDistance = source.calculateRowDistance(target);
        int columnDistance = source.calculateColumnDistance(target);
        return Direction.createDirectionsByDistance(rowDistance, columnDistance);
    }

    private static List<Direction> createDirectionsByDistance(int rowDistance, int columnDistance) {
        validateDistance(rowDistance, columnDistance);
        List<Direction> directions = new ArrayList<>();
        while (rowDistance != 0 || columnDistance != 0) {
            Direction direction = of(rowDistance, columnDistance);
            directions.add(direction);
            rowDistance -= direction.rowDistance;
            columnDistance -= direction.columnDistance;
        }
        return directions;
    }

    private static void validateDistance(int rowDistance, int columnDistance) {
        if (rowDistance == 0 && columnDistance == 0) {
            throw new IllegalArgumentException("제자리 경로를 생성할 수 없습니다.");
        }
    }

    public boolean isOrthogonal() {
        return this == UP || this == DOWN || this == LEFT || this == RIGHT;
    }

    public boolean isDiagonal() {
        return !isOrthogonal();
    }

    public boolean isUpSide() {
        return this.rowDistance == 1;
    }

    public boolean isDownside() {
        return this.rowDistance == -1;
    }

    public boolean isRightSide() {
        return this.columnDistance == 1;
    }

    public boolean isLeftSide() {
        return this.columnDistance == -1;
    }
}
