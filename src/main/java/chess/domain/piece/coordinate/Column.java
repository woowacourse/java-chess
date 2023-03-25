package chess.domain.piece.coordinate;

import java.util.Arrays;

public enum Column {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private static final int INDEX_NUMBER_APPLIER = 1;
    private final String column;
    private final int columnIndex;

    Column(String column, int columnIndex) {
        this.column = column;
        this.columnIndex = columnIndex;
    }

    public static Column fromName(String otherColumn) {
        return Arrays.stream(values())
            .filter(value -> value.column.equals(otherColumn))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 열입니다"));
    }

    public static String symbolFromIndex(int index) {
        return Arrays.stream(values())
            .filter(value -> value.columnIndex == index)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 열입니다")).column;
    }

    public static int indexFromColumn(Column column) {
        return column.columnIndex;
    }

    public int subtract(Column targetColumn) {
        return this.columnIndex - targetColumn.columnIndex;
    }

    public Column up(int num) {
        return values()[columnIndex + num - INDEX_NUMBER_APPLIER];
    }

    public int compareIndex(Column otherColumn) {
        return Integer.compare(otherColumn.columnIndex, this.columnIndex);
    }

    public int realFieldIndex() {
        return columnIndex - INDEX_NUMBER_APPLIER;
    }

}
