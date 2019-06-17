package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Column {
    private static final Map<String, Column> COLUMNS = new HashMap<>();
    static final char MIN = 'a';
    static final char MAX = 'h';

    private final int column;


    static {
        for (int i = MIN; i < MAX; i++) {
            String columnName = String.valueOf((char) i);
            COLUMNS.put(columnName, new Column(i));
        }
    }

    private Column(final int column) {
        this.column = column;
    }

    static Column from(final String column) {
        Optional<Column> optColumn = Optional.ofNullable(COLUMNS.get(column));
        return optColumn.orElseThrow(IllegalArgumentException::new);
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
}
