package chess.board;

import java.util.Arrays;

public enum Column {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private static final int MAX_COLUMN = 7;

    private final String xCoordinate;
    private final int index;

    Column(String xCoordinate, int index) {
        this.xCoordinate = xCoordinate;
        this.index = index;
    }

    public static Column getByIndex(int index) {
        return Arrays.stream(Column.values())
            .filter(column -> column.index == index)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public String getXCoordinate() {
        return xCoordinate;
    }

    public Column opposite() {
        return getByIndex(MAX_COLUMN - index);
    }

    public int getXIndex() {
        return index;
    }
}
