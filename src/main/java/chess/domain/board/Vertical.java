package chess.domain.board;

import java.util.Arrays;

public enum Vertical {
    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
    ONE("1");

    private final String value;

    Vertical(String verticalValue) {
        this.value = verticalValue;
    }

    public static Vertical find(final String value) {
        return Arrays.stream(Vertical.values())
                .filter(vertical -> vertical.value.equals(value))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
