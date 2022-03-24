package chess.domain.position;

import chess.domain.piece.Direction;
import java.util.Objects;

public class Position {
    private final Row row;
    private final Column column;

    public Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public Position(String value) {
        this(Row.of(value.substring(0, 1)), Column.of(value.substring(1, 2)));
    }

    public Direction findDirection(Position position) {
        int x = row.calculateIndex(position.row);
        int y = column.calculateIndex(position.column);

        x = convertCompactValue(x);
        y = convertCompactValue(y);

        return Direction.of(x, y);
    }

    private int convertCompactValue(int value) {
        if (value == 0) {
            return 0;
        }
        return value / Math.abs(value);
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
