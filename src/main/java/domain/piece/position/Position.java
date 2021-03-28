package domain.piece.position;

import domain.exception.InvalidPositionException;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Position {
    private static final Position EMPTY_POSITION = new Position(null, null);    // TODO : null 대신 다른 방법은?

    private final Row row;
    private final Column column;

    public Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position valueOf(int row, int column) {
        return new Position(Row.findRow(row), Column.findColumn(column));
    }

    public static Position of(String input) {
        validateLength(input);
        Column col = Column.convertColumn(input.charAt(0));
        Row row = Row.convertRow(input.charAt(1));
        return new Position(row, col);
    }

    private static void validateLength(String input) {
        if (input.length() > 2 || input.length() <= 0) {
            throw new InvalidPositionException();
        }
    }

    public int getRow() {
        return row.getIndex();
    }

    public int getColumn() {
        return column.getIndex();
    }

    public Position move(Direction direction) {
        try {
            return Position.valueOf(row.getIndex() + direction.getX(), column.getIndex() + direction.getY());
        } catch (InvalidPositionException e) {
            return EMPTY_POSITION;
        }
    }

    public boolean notEmptyPosition() {
        return !this.equals(EMPTY_POSITION);
    }

    public static Queue makeDiff(Position from, Position to) {
        Queue<Integer> diff = new LinkedList<>();
        diff.add(to.getRow() - from.getRow());
        diff.add(to.getColumn() - from.getColumn());
        return diff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
