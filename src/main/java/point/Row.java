package point;

import java.util.Objects;

public enum Row {

    //TODO enum 의 first,second 이딴 값 이름 수정

    FIRST("1", 7),
    SECOND("2", 6),
    THIRD("3", 5),
    FOURTH("4", 4),
    FIFTH("5", 3),
    SIXTH("6", 2),
    SEVENTH("7", 1),
    EIGHTH("8", 0);

    private final String value;
    private final int index;

    Row(final String value, final int index) {
        this.value = value;
        this.index = index;
    }

    public static Row from(char input) {
        for (Row row : values()) {
            if (row.value.equals(String.valueOf(input))) {
                return row;
            }
        }
        throw new IllegalArgumentException("row 없음");
    }

    public static Row from(int targetIndex) {
        for (Row row : values()) {
            if (Objects.equals(row.index, targetIndex)) {
                return row;
            }
        }
        throw new IllegalArgumentException("row 없음");
    }

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }
}
