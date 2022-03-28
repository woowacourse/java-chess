package chess.position;

import chess.piece.Color;

import java.util.Objects;

public class Position {

    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean isFirstPosition(Color color) {
        if (color == Color.WHITE) {
            return row == 6;
        }
        if (color == Color.BLACK) {
            return row == 1;
        }
        return false;
    }

    public boolean isSmallRow(Position position) {
        return this.row < position.row;
    }

    public boolean isSmallColumn(Position position) {
        return this.column < position.column;
    }

    public boolean isSameRow(Position position) {
        return this.row == position.row;
    }

    public boolean isSameColumn(Position position) {
        return this.column == position.column;
    }

    public Position findPossiblePosition(int moveRow, int moveColumn) {
        return new Position(row + moveRow, column + moveColumn);
    }

    public Position findPossiblePosition(Direction direction) {
        return new Position(direction.moveRow(row), direction.moveColumn(column));
    }

    public int gapTwoPositionRow(Position position) {
        return Math.abs(this.row - position.row);
    }

    public int gapTwoPositionColumn(Position position) {
        return Math.abs(this.column - position.column);
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
