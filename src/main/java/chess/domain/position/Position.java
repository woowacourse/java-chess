package chess.domain.position;

import chess.domain.piece.Direction;
import java.util.Objects;

public class Position {

    private final Column column;
    private final Row row;

    public Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public Position(String value) {
        this(Column.of(value.substring(0, 1)), Row.of(value.substring(1, 2)));
    }

    public Direction findDirection(Position position, boolean isOne) {
        int x = column.calculateIndex(position.column);
        int y = row.calculateIndex(position.row);

        if (isOne) {
            return Direction.of(x, y);
        }

        int nx = convertCompactValue(x, y);
        int ny = convertCompactValue(y, x);

        return Direction.of(nx, ny);
    }

    private int convertCompactValue(int target, int other) {
        if (target == 0) {
            return 0;
        }
        if (check((float) other / Math.abs(target))) {
            return target / Math.abs(target);
        }
        return target;
    }

    private boolean check(float target) {
        return target == 0 || target == 1 || target == -1;
    }

    public boolean isSameRow(Row row) {
        return this.row == row;
    }

    public boolean isSameColumn(Position position) {
        return this.column == position.column;
    }

    public Position move(Direction direction) {
        return new Position(column.move(direction.getColumn()), row.move(direction.getRow()));
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
        return "Position{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }
}
