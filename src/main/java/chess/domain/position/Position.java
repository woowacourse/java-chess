package chess.domain.position;

public class Position {

    private final Column column;
    private final Row row;

    public Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position of(String position) {
        if (position.length() != 2) {
            throw new IllegalArgumentException();
        }

        return new Position(
                Column.from(position.charAt(0)),
                Row.from(position.charAt(1)));
    }

    public static int differenceOfRow(Position from, Position to) {
        return Row.differance(from.row, to.row);
    }

    public static int differenceOfColumn(Position from, Position to) {
        return Column.differance(from.column, to.column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }

        Position position = (Position) o;

        if (row != position.row) {
            return false;
        }
        return column == position.column;
    }

    @Override
    public int hashCode() {
        int result = row != null ? row.hashCode() : 0;
        result = 31 * result + (column != null ? column.hashCode() : 0);
        return result;
    }
}
