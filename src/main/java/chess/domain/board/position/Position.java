package chess.domain.board.position;

import java.util.Objects;

public class Position {

    private static final int GCD_LIMIT_MINIMUM = 0;

    private final Column column;
    private final Row row;

    public Position(final int column, final int row) {
        this.column = Column.from(column);
        this.row = Row.from(row);
    }

    public Position(final char column, final char row) {
        this.column = Column.from(column);
        this.row = Row.from(row);
    }

    public Movement convertMovement(Position from) {

        int rowGap = calculateRowBetween(from);
        int columnGap = calculateColumnBetween(from);

        int gcd = calculateGCD(Math.max(rowGap, columnGap), Math.min(rowGap, columnGap));

        return Movement.of(columnGap / gcd, rowGap / gcd);
    }

    private int calculateGCD(int max, int min) {
        if (min == GCD_LIMIT_MINIMUM) {
            return Math.abs(max);
        }

        return calculateGCD(min, max % min);
    }

    public Position moveBy(Movement movement) {
        return movement.nextPosition(column, row);
    }

    public int calculateRowBetween(final Position from) {
        return row.differenceBetween(from.row);
    }

    public int calculateColumnBetween(final Position from) {
        return column.differenceBetween(from.column);
    }

    public Column column() {
        return column;
    }

    public Row row() {
        return row;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
