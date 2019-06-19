package chess.domain;

import java.util.*;

public class Column implements Comparable<Column> {
    private static final Map<String, Column> COLUMNS = new HashMap<>();
    static final char MIN = 'a';
    static final char MAX = 'h';

    private final int column;

    static {
        for (int i = MIN; i <= MAX; i++) {
            String columnName = String.valueOf((char) i);
            COLUMNS.put(columnName, new Column(i));
        }
    }

    private Column(final int column) {
        this.column = column;
    }

    static Column from(final int column) {
        return from(String.valueOf((char) column));
    }

    static Column from(final String column) {
        Optional<Column> optColumn = Optional.ofNullable(COLUMNS.get(column));
        return optColumn.orElseThrow(IllegalArgumentException::new);
    }

    public Column next(final int delta) {
        return Column.from(this.column + delta);
    }

    public int calculateAbsolute(final Column other) {
        return Math.abs(this.column - other.column);
    }

    public int calculateSubtraction(final Column other) {
        return this.column - other.column;
    }

    public static List<Column> values() {
        return new ArrayList<>(COLUMNS.values());
    }

    @Override
    public int compareTo(final Column o) {
        return this.column - o.column;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Column column1 = (Column) o;
        return column == column1.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column);
    }

    @Override
    public String toString() {
        return String.valueOf((char) column);
    }
}
