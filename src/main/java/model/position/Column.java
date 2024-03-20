package model.position;

import java.util.Objects;

public enum Column {

    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7),
    ;

    private final String value;
    private final int index;

    Column(final String value, final int index) {
        this.value = value;
        this.index = index;
    }

    public static Column from(char input) {
        for (Column column : values()) {
            if (column.value.equals(String.valueOf(input))) {
                return column;
            }
        }
        throw new IllegalArgumentException("col 없음");
    }

    public static Column from(int targetIndex) {
        for (Column column : values()) {
            if (Objects.equals(column.index, targetIndex)) {
                return column;
            }
        }
        throw new IllegalArgumentException("col 없음");
    }

    public String getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }
}
