package chess.domain.piece;

import java.util.Arrays;

public enum Color {
    BLACK("블랙"),
    WHITE("화이트"),
    NONE("");

    private final String value;

    Color(String value) {
        this.value = value;
    }

    public static Color find(String name) {
        return Arrays.stream(values())
                .filter(color -> color.name().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public Color switchColor() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public String getValue() {
        return value;
    }
}
