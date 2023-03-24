package chess.domain.position;

import chess.domain.math.UnitVector;
import java.util.Objects;

public final class Position {

    private static final int COLUMN_INDEX = 0;
    private static final int ASCII_SMALL_A = 97;
    private static final int ROW_INDEX = 1;
    private static final int BOARD_MAX_ROW = 8;

    private final Row row;
    private final Column column;

    public Position(final Row row, final Column column) {
        this.row = row;
        this.column = column;
    }

    public Position(final Position otherPosition) {
        this.row = otherPosition.row;
        this.column = otherPosition.column;
    }

    public Position(final int row, final int column) {
        this.row = new Row(row);
        this.column = new Column(column);
    }

    public Position move(final UnitVector unitVector) {
        Row nextRow = row.add(unitVector.getRow());
        Column nextColumn = column.add(unitVector.getColumn());

        return new Position(nextRow, nextColumn);
    }

    public static Position toPosition(final String input) {
        String value = input.toLowerCase();

        char columnValue = value.charAt(COLUMN_INDEX);
        int column = Math.abs(columnValue - ASCII_SMALL_A);

        char rowValue = value.charAt(ROW_INDEX);
        int row = Math.abs(Character.getNumericValue(rowValue) - BOARD_MAX_ROW);

        return new Position(row, column);
    }

    public int getRow() {
        return row.getRow();
    }

    public int getColumn() {
        return column.getColumn();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(row, position.row) && Objects.equals(column, position.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
