package chess;

import java.util.Arrays;

public enum Row {
    ONE("1", 0),
    TWO("2", 1),
    THREE("3", 2),
    FOUR("4", 3),
    FIVE("5", 4),
    SIX("6", 5),
    SEVEN("7", 6),
    EIGHT("8", 7);

    private static final int ROW_MAX = Arrays.stream(Row.values())
        .mapToInt(row -> row.index)
        .max()
        .orElseThrow(IllegalArgumentException::new);

    private final String yCoordinate;
    private final int index;

    Row(String yCoordinate, int index) {
        this.yCoordinate = yCoordinate;
        this.index = index;
    }

    public String getYCoordinate() {
        return yCoordinate;
    }

    public Row opposite() {
        return getByIndex(ROW_MAX - index);
    }

    public Row getByIndex(int index) {
        return Arrays.stream(Row.values())
            .filter(row -> row.index == index)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }
}
