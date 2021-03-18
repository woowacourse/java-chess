package chess.domain.piece;

public class Position {
    private final int row;
    private final int column;

    public Position(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

    public double calculateGradient(Position position) {
        if (column == position.column) {
            throw new ArithmeticException("컬럼이 동일하면 기울기를 구할 수 없습니다.");
        }

        return (position.row - row) / (double) (position.column - column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
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
