package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Row {
    private static final Map<String, Row> ROWS = new HashMap<>();
    static final int MIN = 1;
    static final int MAX = 8;

    private final String row;

    static {
        for (int i = MIN; i <= MAX; i++) {
            String rowName = Integer.toString(i);
            ROWS.put(rowName, new Row(rowName));
        }
    }

    private Row(final String row) {
        this.row = row;
    }

    static Row from(final String row) {
        Optional<Row> optRow = Optional.ofNullable(ROWS.get(row));
        return optRow.orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Row row1 = (Row) o;
        return Objects.equals(row, row1.row);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row);
    }
}
