package point;

import java.util.Objects;

public class Position { //TODO row, column 위치 변경

    //TODO : 64개 캐싱

    private final Row row;
    private final Column column;

    public Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position from(String command) {
        return new Position(Row.from(command.charAt(1)), Column.from(command.charAt(0)));
    }

    public int getRow() {
        return row.getIndex();
    }

    public int getColumn() {
        return column.getIndex();
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof Position position)) {
            return false;
        }
        return Objects.equals(row, position.row) && Objects.equals(column, position.column);
    }

    @Override
    public String toString() {
        return "(" + row.getIndex() + ", " + column.getIndex() + ")";
    }
}
