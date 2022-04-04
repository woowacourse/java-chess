package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Position {

    public static final int MIN = 1;
    public static final int MAX = 8;

    private static final String INVALID_RANGE_ERROR = "행과 열은 각각 1이상 8이하의 수여야 합니다.";
    private static final String INVALID_STRING_ERROR = "행은 a-h, 열은 1-8이어야 합니다.";
    private static final String ROW_CACHE = "abcdefgh";
    private static final String COLUMN_CACHE = "12345678";

    private final int row;
    private final int column;

    public Position(int row, int column) {
        validatePosition(row, column);
        this.row = row;
        this.column = column;
    }

    public Position(String fileAndRank) {
        String row = String.valueOf(fileAndRank.charAt(0));
        String column = String.valueOf(fileAndRank.charAt(1));
        validatePosition(row, column);
        this.row = COLUMN_CACHE.indexOf(column) + 1;
        this.column = ROW_CACHE.indexOf(row) + 1;
    }

    private void validatePosition(int row, int column) {
        if (row < MIN || row > MAX || column < MIN || column > MAX) {
            throw new IllegalArgumentException(INVALID_RANGE_ERROR);
        }
    }

    private void validatePosition(String row, String column) {
        if (!ROW_CACHE.contains(row) || !COLUMN_CACHE.contains(column)) {
            throw new IllegalArgumentException(INVALID_STRING_ERROR);
        }
    }

    public int subtractRow(Position position) {
        return this.row - position.row;
    }

    private int calculateTimes(Position to) {
        int rowAbsoluteDifference = Math.abs(this.subtractRow(to));
        int columnAbsoluteDifference = Math.abs(this.subtractColumn(to));
        if (rowAbsoluteDifference == 0 && columnAbsoluteDifference != 0) {
            return columnAbsoluteDifference;
        }
        if (rowAbsoluteDifference != 0 && columnAbsoluteDifference == 0) {
            return rowAbsoluteDifference;
        }
        return Math.min(rowAbsoluteDifference, columnAbsoluteDifference);
    }

    public boolean canReachUnderThreshold(Position to, int threshold) {
        return calculateTimes(to) <= threshold;
    }

    public List<Position> backtrackPath(Position to) {
        List<Position> path = new ArrayList<>();
        int totalTimes = calculateTimes(to);
        int unitRow = to.subtractRow(this) / totalTimes;
        int unitColumn = to.subtractColumn(this) / totalTimes;
        for (int eachTime = 1; eachTime < totalTimes; eachTime++) {
            path.add(new Position(this.row + eachTime * unitRow, this.column + eachTime * unitColumn));
        }
        return path;
    }

    public int subtractColumn(Position position) {
        return this.column - position.column;
    }

    public boolean isSameRow(int row) {
        return this.row == row;
    }

    public boolean isSameColumn(int column) {
        return this.column == column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    public String getPosition() {
        Map<Integer, String> columnCache = Map.of(1, "a", 2,"b", 3, "c", 4, "d",
                5, "e", 6 , "f", 7, "g", 8, "h");
        return columnCache.get(this.column) + this.row;
    }
}
