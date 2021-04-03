package chess.domain.piece;

public class Position {
    private static final String[] columnStr = {"a", "b", "c", "d", "e", "f", "g", "h"};

    private final int row;
    private final int column;

    public Position(String[] stringPosition) {
        this(8-Integer.parseInt(stringPosition[1]), stringPosition[0].toCharArray()[0] - 'a');
    }

    public Position(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

    public double calculateGradient(Position position) {
        return (position.row - row) / (double) (position.column - column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return columnStr[column] + (row+1);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        final Position position = (Position) o;

        if (row != position.row) return false;
        return column == position.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }
}
