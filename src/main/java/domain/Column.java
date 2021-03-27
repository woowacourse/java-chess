package domain;

import java.util.Arrays;

public enum Column {
    ZERO('a', 0),
    ONE('b', 1),
    TWO('c', 2),
    THREE('d', 3),
    FOUR('e', 4),
    FIVE('f', 5),
    SIX('g', 6),
    SEVEN('h', 7);

    private char name;
    private int index;

    Column(char name, int index) {
        this.name = name;
        this.index = index;
    }

    public static int convertColumn(char input) {
        return Arrays.stream(Column.values()).
                filter(value -> value.name == input)
                .findAny()
                .get()
                .index;
    }
}
