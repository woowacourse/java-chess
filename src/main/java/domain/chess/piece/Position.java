package domain.chess.piece;

import domain.chess.Board;

import java.util.Objects;

public class Position {
    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Position Of(int x, int y) {
        validateIndex(x, y);
        return new Position(x, y);
    }

    private static void validateIndex(int x, int y) {
        if (x < 0 || x >= Board.SIZE || y < 0 || y >= Board.SIZE) {
            throw new IllegalArgumentException("위치가 잘못되었습니다.(x : " + x + ", y : " + y + ")");
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
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
