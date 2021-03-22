package chess.domain.board;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum Column {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private static final int MAX_COLUMN = 7;
    private static final List<Column> COLUMNS = Arrays.asList(Column.values());

    static {
        COLUMNS.sort(Comparator.comparingInt(Column::index));
    }

    private final String coordinate;

    Column(String coordinate) {
        this.coordinate = coordinate;
    }

    public static Column columnByIndex(int index) {
        return Arrays.stream(Column.values())
            .filter(column -> column.index() == index)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public static List<Column> columns() {
        return Collections.unmodifiableList(COLUMNS);
    }

    public Column opposingColumn() {
        return columnByIndex(MAX_COLUMN - index());
    }

    public String coordinate() {
        return coordinate;
    }

    public int index() {
        return ordinal();
    }
}
