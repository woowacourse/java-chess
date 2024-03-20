package chess.domain;

import java.util.Arrays;

public enum Column {
    a("a", 0),
    b("b", 1),
    c("c", 2),
    d("d", 3),
    e("e", 4),
    f("f", 5),
    g("g", 6),
    h("h", 7);

    private String name;
    private int index;

    Column(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Column calculateNextColumn(int distance) {
        return Arrays.stream(values())
                .filter(column -> column.index == this.index + distance)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 Column 가 없습니다."));
    }

    public boolean isNextInRange(int distance) {
        int nextIndex = index + distance;
        return a.index <= nextIndex && nextIndex <= h.index;
    }
}
