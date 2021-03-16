package chess.domain.board;

import java.util.Arrays;

public enum Vertical {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

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
