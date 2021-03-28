package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public enum Column {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    public static final String OVER_RANGE_ERROR = "범위를 넘어가는 move 입니다";
    private final String value;
    private static Column[] columns = values();

    Column(String value) {
        this.value = value;
    }

    public Column moveBy(final int value) {
        if ((this.ordinal() + value) < 0 || this.ordinal() + value >= columns.length) {
            throw new IllegalArgumentException(OVER_RANGE_ERROR);
        }
        return columns[(this.ordinal() + value)];
    }

    public String value() {
        return value;
    }

    public List<Column> getBetween(final Column to) {
        final int start = Math.min(this.ordinal(), to.ordinal());
        final int end = Math.max(this.ordinal(), to.ordinal());
        final List<Column> betweenColumns = new ArrayList<>();
        IntStream.range(start + 1, end)
                 .forEach(x -> betweenColumns.add(columns[x]));
        return betweenColumns;
    }

    public int diff(final Column column) {
        return column.ordinal() - ordinal();
    }

    public int unitDirection(final Column to) {
        return diff(to) / Math.abs(diff(to));
    }
}
