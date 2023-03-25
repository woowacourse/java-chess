package chess.domain.position;

import chess.domain.math.UnitVector;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Position {

    private static final int COLUMN_INDEX = 0;
    private static final int ASCII_SMALL_A = 97;
    private static final int ROW_INDEX = 1;
    private static final int MAX_INDEX = 8;
    private static final List<Position> CACHE = new ArrayList<>();

    static {
        for (int row = 0; row < MAX_INDEX; row++) {
            for (int column = 0; column < MAX_INDEX; column++) {
                CACHE.add(new Position(row, column));
            }
        }
    }

    private final Row row;
    private final Column column;

    private Position(final int row, final int column) {
        this.row = new Row(row);
        this.column = new Column(column);
    }

    private Position(final Row row, final Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(final int row, final int column) {
        return CACHE.get(CACHE.indexOf(new Position(new Row(row), new Column(column))));
    }

    public static Position from(final Position position) {
        return CACHE.get(CACHE.indexOf(position));
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
        int row = Math.abs(Character.getNumericValue(rowValue) - MAX_INDEX);

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
