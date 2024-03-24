package chess.model.position;

import java.util.Objects;

public class Position {

    private static final int VALID_COORDINATE_LENGTH = 2;
    private static final int COLUMN_INDEX = 0;
    private static final int ROW_INDEX = 1;

    private final Column column;
    private final Row row;

    public Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position from(String coordinate) {
        if (coordinate.length() != VALID_COORDINATE_LENGTH) {
            throw new IllegalArgumentException("좌표 값은 2자리여야 합니다.");
        }
        Column column = Column.findColumn(coordinate.charAt(COLUMN_INDEX));
        Row row = Row.findRow(coordinate.charAt(ROW_INDEX));
        return new Position(column, row);
    }

    public int calculateColumnOffSet(Position target) {
        Column targetColumn = target.column;
        return targetColumn.getIndex() - column.getIndex();
    }

    public int calculateRowOffSet(Position target) {
        Row targetRow = target.row;
        return targetRow.getIndex() - row.getIndex();
    }

    public boolean isSameRow(Row row) {
        return this.row == row;
    }

    public Column getColumn() {
        return column;
    }

    public Row getRow() {
        return row;
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
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return column.toString() + row.toString();
    }
}
