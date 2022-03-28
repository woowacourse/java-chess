package chess.domain.position;

import chess.domain.piece.Color;

import java.util.Objects;

public class Position {

    private static final int WHITE_FIRST_POSITION = 6;
    private static final int BLACK_FIRST_POSITION = 1;

    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean isFirstPosition(Color color) {
        if (color == Color.WHITE) {
            return row == WHITE_FIRST_POSITION;
        }
        if (color == Color.BLACK) {
            return row == BLACK_FIRST_POSITION;
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

    public int gapTwoPositionRow(Position position) {
        return Math.abs(this.row - position.row);
    }

    public int gapTwoPositionColumn(Position position) {
        return Math.abs(this.column - position.column);
    }

    public Position findPossiblePosition(int moveRow, int moveColumn) {
        return new Position(row + moveRow, column + moveColumn);
    }

    public Position findPossiblePosition(Direction direction) {
        return new Position(direction.moveRow(row), direction.moveColumn(column));
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
