package chess.domain.position;

import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import java.util.Objects;

public class Position {

    private static final int FIRST_BEGIN = 0;
    private static final int FIRST_END = 1;
    private static final int SECOND_BEGIN = 1;
    private static final int SECOND_END = 2;

    private final Column column;
    private final Row row;

    public Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public Position(String value) {
        this(Column.of(value.substring(FIRST_BEGIN, FIRST_END)),
                Row.of(value.substring(SECOND_BEGIN, SECOND_END)));
    }

    public Direction findDirection(Position position, Piece piece) {
        int x = column.calculateIndex(position.column);
        int y = row.calculateIndex(position.row);

        if (piece.isStep()) {
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

    public boolean movable(Direction direction) {
        return column.movable(direction.getColumn()) && row.movable(direction.getRow());
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
