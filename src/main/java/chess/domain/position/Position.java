package chess.domain.position;

public final class Position {

    private static final int MIN_COORDINATE = 0;
    private static final int MAX_COORDINATE = 7;

    private final int column;
    private final int row;

    public Position(final int column, final int row) {
        validate(column, row);
        this.column = column;
        this.row = row;
    }

    private void validate(final int column, final int row) {
        if (isInvalidColum(column) || isInvalidRow(row)) {
            throw new IllegalArgumentException("유효하지 않은 위치입니다.");
        }
    }

    private boolean isInvalidColum(final int column) {
        return MIN_COORDINATE > column || MAX_COORDINATE < column;
    }

    private boolean isInvalidRow(final int row) {
        return MIN_COORDINATE > row || MAX_COORDINATE < row;
    }


    public Position move(final RelativePosition relativePosition) {
        int column = this.column + relativePosition.getX();
        int row = this.row + relativePosition.getY();
        return new Position(column, row);
    }

    public boolean isColumn(final int column) {
        return this.column == column;
    }


    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Position position = (Position) o;

        if (column != position.column)
            return false;
        return row == position.row;
    }

    @Override
    public int hashCode() {
        int result = column;
        result = 31 * result + row;
        return result;
    }
}
