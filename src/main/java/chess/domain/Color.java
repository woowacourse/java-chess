package chess.domain;

import java.util.Arrays;

public enum Color {
    WHITE("white"),
    BLACK("black"),
    EMPTY("empty"),
    ;
    private final String value;

    Color(String value) {
        this.value = value;
    }

    public static Color from(String input) {
        return Arrays.stream(values())
                .filter(turn -> turn.value.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 색상값입니다."));
    }

    public Color nextColor() {
        if (this == WHITE) {
            return BLACK;
        }
        if (this == BLACK) {
            return WHITE;
        }
        return EMPTY;
    }

    public String getValue() {
        return value;
    }
}
