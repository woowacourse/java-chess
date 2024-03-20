package model.position;

import java.util.Objects;

public enum Column {


    //TODO enum 의 first,second 이딴 값 이름 수정
    FIRST("a", 0),
    SECOND("b", 1),
    THIRD("c", 2),
    FOURTH("d", 3),
    FIFTH("e", 4),
    SIXTH("f", 5),
    SEVENTH("g", 6),
    EIGHTH("h", 7),
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
