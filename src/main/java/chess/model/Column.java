package chess.model;

import java.util.HashMap;
import java.util.Map;

public enum Column {
    A("A", 0),
    B("B", 1),
    C("C", 2),
    D("D", 3),
    E("E", 4),
    F("F", 5),
    G("G", 6),
    H("H", 7);

    private static final String NO_SUCH_VALUE = "존재하지 않는 Column 값입니다.";
    private static final Map<Integer, Column> map = initializeMap();

    private String name;
    private int columnIndex;

    Column(final String name, final int columnIndex) {
        this.name = name;
        this.columnIndex = columnIndex;
    }

    private static Map<Integer, Column> initializeMap() {
        final Map<Integer, Column> map = new HashMap<>();
        for (Column value : values()) {
            map.put(value.columnIndex, value);
        }
        return map;
    }

    //    public int getIndexByName(final String name) {
    //        for (Column value : Column.values()) {
    //            if (value.name.toUpperCase().equals(name)) {
    //                return value.columnIndex;
    //            }
    //        }
    //        throw new IllegalArgumentException(NO_SUCH_VALUE);
    //    }
    //
    //    public String getNameByIndex(final int columnIndex) {
    //        for (Column value : Column.values()) {
    //            if (value.columnIndex == columnIndex) {
    //                return value.name;
    //            }
    //        }
    //        throw new IllegalArgumentException(NO_SUCH_VALUE);
    //    }
    public static Column getByIndex(final int columnIndex) {
        final Column value = map.get(columnIndex);
        if (value == null) {
            throw new IllegalArgumentException(NO_SUCH_VALUE);
        }
        return value;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public boolean isFirst() {
        return this == Column.A;
    }

    public boolean isLast() {
        return this == Column.H;
    }

    @Override
    public String toString() {
        return "Column " + this.name;
    }
}
