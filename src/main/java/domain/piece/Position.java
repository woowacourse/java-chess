package domain.piece;

import domain.Column;
import domain.Row;
import domain.exception.InvalidPositionException;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Position {
    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(String input) {
        validateLength(input);
        Column col = Column.convertColumn(input.charAt(0));
        Row row = Row.convertRow(input.charAt(1));
        return Position.valueOf(row.getIndex(), col.getIndex());
    }

    private static void validateLength(String input) {
        if (input.length() > 2 || input.length() <= 0) {
            throw new InvalidPositionException();
        }
    }

    public static Position valueOf(int row, int column) {
        return new Position(row, column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Position move(Direction direction) {
        return Position.valueOf(row + direction.getX(), column + direction.getY());
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
