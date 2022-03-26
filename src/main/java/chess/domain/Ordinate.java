package chess.domain;

import java.util.Arrays;

public enum Ordinate {

    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
    ONE("1");

    private final String value;

    Ordinate(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getCoordinate() {
        return Integer.parseInt(value);
    }

    public static Ordinate findOrdinate(int value) {
        return Arrays.stream(Ordinate.values())
            .filter(ordinate -> Integer.parseInt(ordinate.value) == value)
            .findAny()
            .orElseThrow();
    }
}
