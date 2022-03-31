package chess.domain.piece;

import java.util.List;
import java.util.function.BiPredicate;

public enum Direction {
    NORTH(0, 1, (row, col) -> row > 0 && col == 0),
    NORTHEAST(1, 1, (row, col) -> Math.abs(row) == Math.abs(col) && row > 0 && col > 0),
    NORTH_TWO_STEP(0, 2, (row, col) -> row == 2 && col == 0),
    EAST(1, 0, (row, col) -> row == 0 && col > 0),
    SOUTHEAST(1, -1, (row, col) -> Math.abs(row) == Math.abs(col) && row < 0 && col > 0),
    SOUTH(0, -1, (row, col) -> row < 0 && col == 0),
    SOUTHWEST(-1, -1, (row, col) -> Math.abs(row) == Math.abs(col) && col < 0 && row < 0),
    SOUTH_TWO_STEP(0, -2, (row, col) -> row == -2 && col == 0),
    WEST(-1, 0, (row, col) -> row == 0 && col < 0),
    NORTHWEST(-1, 1, (row, col) -> Math.abs(row) == Math.abs(col) && col < 0 && row > 0),

    NNE(1, 2, (row, col) -> row == 2 && col == 1),
    NNW(-1, 2, (row, col) -> row == 2 && col == -1),
    SSE(1, -2, (row, col) -> row == -2 && col == 1),
    SSW(-1, -2, (row, col) -> row == -2 && col == -1),
    EEN(2, 1, (row, col) -> row == 1 && col == 2),
    EES(2, -1, (row, col) -> row == -1 && col == 2),
    WWN(-2, 1, (row, col) -> row == 1 && col == -2),
    WWS(-2, -1, (row, col) -> row == -1 && col == -2);

    private final int xDegree;
    private final int yDegree;
    private final BiPredicate<Integer, Integer> predicate;

    private Direction(int xDegree, int yDegree, BiPredicate<Integer, Integer> predicate) {
        this.xDegree = xDegree;
        this.yDegree = yDegree;
        this.predicate = predicate;
    }

    public int getXDegree() {
        return xDegree;
    }

    public int getYDegree() {
        return yDegree;
    }

    public static Direction find(int rowDifference, int colDifference, List<Direction> directions) {
        return directions.stream()
                .filter(direction -> direction.predicate.test(rowDifference, colDifference))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치로 말이 움직일 수 없습니다."));
    }

    @Override
    public String toString() {
        return "Direction{" +
                "xDegree=" + xDegree +
                ", yDegree=" + yDegree +
                ", predicate=" + predicate +
                '}';
    }
}
