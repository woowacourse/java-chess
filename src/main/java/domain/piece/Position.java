package domain.piece;

import domain.IndexMachine;

import java.util.Objects;

public class Position {
    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(String input) {
        return IndexMachine.convertPosition(input);
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
