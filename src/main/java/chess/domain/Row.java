package chess.domain;

import java.util.Arrays;

public enum Row {
    RANK1("1", 7),
    RANK2("2", 6),
    RANK3("3", 5),
    RANK4("4", 4),
    RANK5("5", 3),
    RANK6("6", 2),
    RANK7("7", 1),
    RANK8("8", 0);

    private String name;
    private int index;

    Row(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Row calculateNextRow(int distance) {
        return Arrays.stream(values())
                .filter(row -> row.index == this.index + distance)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 Column 이 없습니다."));
    }

    public boolean isNextInRange(int distance) {
        int nextIndex = index + distance;
        return RANK8.index <= nextIndex && nextIndex <= RANK1.index;
    }
}
