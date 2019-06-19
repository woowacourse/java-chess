package chess.domain;

import java.util.*;

public class Position implements Comparable<Position> {
    private static final Map<String, Position> POSITIONS = new HashMap<>();

    private static final int DIAGONAL_INCLINATION = 1;
    private static final int RIGHT_INCLINATION = 0;
    private static final int ZERO = 0;

    static {
        for (final Column column : Column.values()) {
            for (final Row row : Row.values()) {
                String key = column.toString() + row.toString();
                POSITIONS.put(key, new Position(row, column));
            }
        }
    }

    private final Row row;
    private final Column column;

    private Position(final Row row, final Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(final Row row, final Column column) {
        String key = column.toString() + row.toString();
        return POSITIONS.get(key);
    }

    public static Position of(final String row, final String column) {
        return Position.of(Row.from(row), Column.from(column));
    }

    public boolean isDiagonal(final Position target) {
        int height = this.row.calculateAbsolute(target.row);
        int width = this.column.calculateAbsolute(target.column);
        if (width == ZERO) {
            return false;
        }
        return Math.abs(height / width) == DIAGONAL_INCLINATION;
    }

    public boolean isPerpendicular(final Position target) {
        return this.column.calculateAbsolute(target.column) == RIGHT_INCLINATION;
    }

    public boolean isLevel(final Position target) {
        return this.row.calculateAbsolute(target.row) == RIGHT_INCLINATION;
    }

    public boolean isMoveAnyWhereSum(final Position position, int distance) {
        return sumRowAndColumn(position) == distance;
    }

    public boolean isMoveAnyWhereSub(final Position position, int distance) {
        return subRowAndColumn(position) == distance;
    }

    public int subRowAndColumn(final Position target) {
        return Math.abs(this.distanceOfRow(target) - this.distanceOfColumn(target));
    }

    public int sumRowAndColumn(final Position target) {
        return Math.abs(this.distanceOfRow(target) + this.distanceOfColumn(target));
    }

    public List<Position> findRoutes(Position target) {
        int gapOfRow = this.row.calculateSubtraction(target.row);
        int gapOfColumn = this.column.calculateSubtraction(target.column);

        List<Position> positions = new ArrayList<>();
        // 나이트 같은 대각선, 가로, 세로 아닌 경우

        // 세로
        if (isPerpendicular(target)) {
            int delta = gapOfRow < 0 ? +1 : -1;
            Row current = this.row.next(delta);
            while (!current.equals(target.row)) {
                positions.add(Position.of(current, this.column));
                current = current.next(delta);
            }
        }
        // 가로
        if (isLevel(target)) {
            int delta = gapOfColumn < 0 ? +1 : -1;
            Column current = this.column.next(delta);
            while (!current.equals(target.column)) {
                positions.add(Position.of(this.row, current));
                current = current.next(delta);
            }
        }

        // 대각선
        if (isDiagonal(target)) {

        }
        positions.add(target);
        return positions;

    }

//    private Position plus(final Position target) {
////        this.row.add()
//    }
//
//    private Position minus(final Position target) {
//
//    }

    private int distanceOfRow(final Position target) {
        return this.row.calculateAbsolute(target.row);
    }

    private int distanceOfColumn(final Position target) {
        return this.column.calculateAbsolute(target.column);
    }

    @Override
    public int compareTo(final Position o) {
        int result = this.row.compareTo(o.row);

        if (result == 0) {
            result = this.column.compareTo(o.column);
        }

        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return Objects.equals(row, position.row) &&
                Objects.equals(column, position.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
