package domain;

import domain.exception.InvalidPositionException;

import java.util.Arrays;

public enum Row {
    ZERO('8', 0),
    ONE('7', 1),
    TWO('6', 2),
    THREE('5', 3),
    FOUR('4', 4),
    FIVE('3', 5),
    SIX('2', 6),
    SEVEN('1', 7);

    private char name;
    private int index;

    Row(char name, int index) {
        this.name = name;
        this.index = index;
    }

    public static Row convertRow(char input) {
        return Arrays.stream(Row.values()).
                filter(value -> value.name == input)
                .findAny()
                .orElseThrow(() -> new InvalidPositionException());
    }

    public int getIndex() {
        return index;
    }
}
