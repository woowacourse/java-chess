package chess.domain.position;

import java.util.Objects;

public class Position {
    private final Row row;
    private final Column column;

    private Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position from(String position) {
        Row row = Row.valueOf(position.substring(0, 1));
        Column column = Column.valueOf(position.substring(1));

        return new Position(row, column);
    }

    public Position move(int rowDirection, int columnDirection) {
        return new Position(row.update(rowDirection), column.update(columnDirection));
    }

    public int calculateRowDistance(Position target) {
        return Math.abs(row.subtractRow(target.row));
    }

    public int calculateColumnDistance(Position target) {
        return Math.abs(subtractColumns(target));
    }

    public int subtractColumns(Position target) {
        return column.subtractColumn(target.column);
    }

    public boolean isSameColumn(Position target) {
        return this.column == target.column;
    }

    public boolean isSameRow(Position target) {
        return this.row == target.row;
    }

    public int findRowDirection(Position target) {
        return row.findDirection(target.row);
    }

    public int findColumnDirection(Position target) {
        return column.findDirection(target.column);
    }

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Position position = (Position) object;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
