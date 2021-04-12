package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public enum Row {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    public static final String OVER_RANGE_ERROR = "범위를 넘어가는 move 입니다";
    private static final Row[] rows = values();
    private final String value;

    Row(final String value) {
        this.value = value;
    }

    public Row moveBy(final int value) {
        if ((this.ordinal() + value) < 0 || this.ordinal() + value >= rows.length) {
            throw new IllegalArgumentException(OVER_RANGE_ERROR);
        }
        return rows[(this.ordinal() + value)];
    }

    public String value() {
        return value;
    }

    public List<Row> getBetween(final Row to) {
        final int start = Math.min(this.ordinal(), to.ordinal());
        final int end = Math.max(this.ordinal(), to.ordinal());
        final List<Row> betweenRows = new ArrayList<>();
        IntStream.range(start + 1, end)
                .forEach(x -> betweenRows.add(rows[x]));
        return betweenRows;
    }

    public int diff(final Row row) {
        return row.ordinal() - ordinal();
    }

    public int unitDirection(final Row to) {
        return diff(to) / Math.abs(diff(to));
    }
}
