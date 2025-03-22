package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Direction {

    public static List<Movement> calculateDirection(Position departure, Position destination) {
        int columnDistance = destination.column().calculateColumnDistance(departure.column());
        int rowDistance = destination.row().calculateRowDistance(departure.row());
        if (Math.abs(columnDistance) == 0 || Math.abs(rowDistance) == 0) {
            return findDirectDirection(columnDistance, rowDistance);
        }

        if (Math.abs(columnDistance) == Math.abs(rowDistance)) {
            return findDiagonalDirection(columnDistance, rowDistance);
        }
        return findCurveDirection(columnDistance, rowDistance);
    }

    private static List<Movement> findCurveDirection(final int columnDistance, final int rowDistance) {
        int columnCount = Math.abs(columnDistance);
        int rowCount = Math.abs(rowDistance);
        int totalCount = Math.min(columnCount, rowCount);

        if (Math.max(columnCount, rowCount) != 2 || Math.min(columnCount, rowCount) != 1) {
            throw new IllegalArgumentException("맞지 않은 이동입니다");
        }

        if (columnCount > rowCount) {
            if (columnDistance > 0 && rowDistance > 0) {
                return repeat(totalCount, Movement.DOWN_DOWN_RIGHT);
            }
            if (columnDistance > 0 && rowDistance < 0) {
                return repeat(totalCount, Movement.UP_UP_RIGHT);
            }
            if (columnDistance < 0 && rowDistance > 0) {
                return repeat(totalCount, Movement.UP_UP_LEFT);
            }
            if (columnDistance < 0 && rowDistance < 0) {
                return repeat(totalCount, Movement.DOWN_DOWN_LEFT);
            }
        }
        if (columnCount < rowCount) {
            if (columnDistance > 0 && rowDistance > 0) {
                return repeat(totalCount, Movement.RIGHT_RIGHT_DOWN);
            }
            if (columnDistance > 0 && rowDistance < 0) {
                return repeat(totalCount, Movement.RIGHT_RIGHT_UP);
            }
            if (columnDistance < 0 && rowDistance > 0) {
                return repeat(totalCount, Movement.LEFT_LEFT_UP);
            }
            if (columnDistance < 0 && rowDistance < 0) {
                return repeat(totalCount, Movement.LEFT_LEFT_DOWN);
            }
        }
        throw new IllegalArgumentException("방향 탐지 실패");
    }

    private static List<Movement> findDiagonalDirection(final int columnDistance, final int rowDistance) {
        if (columnDistance > 0 && rowDistance > 0) {
            return repeat(Math.abs(rowDistance), Movement.RIGHT_DOWN);
        }
        if (columnDistance > 0 && rowDistance < 0) {
            return repeat(Math.abs(rowDistance), Movement.RIGHT_UP);
        }
        if (columnDistance < 0 && rowDistance > 0) {
            return repeat(Math.abs(columnDistance), Movement.LEFT_DOWN);
        }
        if (columnDistance < 0 && rowDistance < 0) {
            return repeat(Math.abs(columnDistance), Movement.LEFT_UP);
        }
        throw new IllegalArgumentException("방향 탐지 실패");
    }

    private static List<Movement> findDirectDirection(final int columnDistance, final int rowDistance) {
        if (columnDistance == 0 && rowDistance > 0) {
            return repeat(Math.abs(rowDistance), Movement.DOWN);
        }
        if (columnDistance == 0 && rowDistance < 0) {
            return repeat(Math.abs(rowDistance), Movement.UP);
        }
        if (columnDistance > 0 && rowDistance == 0) {
            return repeat(Math.abs(columnDistance), Movement.RIGHT);
        }
        if (columnDistance < 0 && rowDistance == 0) {
            return repeat(Math.abs(columnDistance), Movement.LEFT);
        }
        throw new IllegalArgumentException("방향 탐지 실패");
    }

    private static List<Movement> repeat(int count, Movement movement) {
        List<Movement> movements = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            movements.add(movement);
        }
        return movements;
    }
}
