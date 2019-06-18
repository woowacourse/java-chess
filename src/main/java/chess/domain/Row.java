package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Row implements Comparable<Row> {
    private static final Map<Integer, Row> ROWS = new HashMap<>();
    static final int MIN = 1;
    static final int MAX = 8;

    private final int row;

    static {
        for (int i = MIN; i <= MAX; i++) {
            ROWS.put(i, new Row(i));
        }
    }

    private Row(final int row) {
        this.row = row;
    }

    static Row from(final String row) {
        final int key = Integer.parseInt(row);
        Optional<Row> optRow = Optional.ofNullable(ROWS.get(key));
        return optRow.orElseThrow(IllegalArgumentException::new);
    }

    public int calculateAbsolute(final Row row) {
        return Math.abs(this.row - row.row);
    }

    @Override
    public int compareTo(final Row o) {
        return o.row - this.row;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Row row1 = (Row) o;
        return row == row1.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row);
    }
}
