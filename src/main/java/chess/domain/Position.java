package chess.domain;


import java.util.Objects;

public class Position {
    private static final int FROM_FOR_ROW = 0;
    private static final int TO_FOR_ROW = 1;
    private static final int START_INDEX_FOR_COLUMN = 1;

    private final Row row;
    private final Column column;

    private Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(String input){
        return new Position(
            Row.of(input.substring(START_INDEX_FOR_COLUMN)),
            Column.of(input.substring(FROM_FOR_ROW,TO_FOR_ROW)));
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
}
