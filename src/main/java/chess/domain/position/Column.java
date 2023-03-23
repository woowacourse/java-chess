package chess.domain.position;

import java.util.Objects;

public class Column {

    private static final int MAX_COLUMN = 7;
    private static final int MIN_COLUMN = 0;
    static final String INVALID_RANGE_OF_VALUE = "열은 최소 0, 최대 7을 가질 수 있습니다. 입력값: ";

    private final int column;

    public Column(final int column) {
        validateColumn(column);
        this.column = column;
    }

    private void validateColumn(final int column) {
        if (column < MIN_COLUMN || column > MAX_COLUMN) {
            throw new IllegalArgumentException(INVALID_RANGE_OF_VALUE + column);
        }
    }

    public Column add(final int otherColumn) {
        return new Column(this.column + otherColumn);
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Column column1 = (Column) o;
        return column == column1.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column);
    }
}
