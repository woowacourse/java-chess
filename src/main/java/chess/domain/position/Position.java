package chess.domain.position;

import chess.domain.piece.Color;

import java.util.Objects;

public class Position {

    private static final int WHITE_FIRST_POSITION = 6;
    private static final int BLACK_FIRST_POSITION = 1;
    private static final int MINIMUM_INDEX = 0;
    private static final int MAXIMUM_INDEX = 7;

    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(String rank, String file) {
        return new Position(Rank.findRow(rank), File.findColumn(file));
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

    public boolean isSamePosition(int row, int column) {
        return (this.row == row) && (this.column == column);
    }

    public boolean isSamePosition(Position position) {
        return (this.row == position.row) && (this.column == position.column);
    }

    public boolean isOverRange() {
        return row < MINIMUM_INDEX || column < MINIMUM_INDEX || row > MAXIMUM_INDEX || column > MAXIMUM_INDEX;
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
