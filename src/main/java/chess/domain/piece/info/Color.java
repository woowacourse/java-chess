package chess.domain.piece.info;

import java.util.Arrays;

public enum Color {
    WHITE("WHITE"),
    BLACK("BLACK"),
    NONE("NONE");

    private final String value;
    Color(String value) {
        this.value = value;
    }

    public boolean isSame(Color color) {
        return this == color;
    }

    public Color reverse() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public static Color from(String input) {
        return Arrays.stream(values())
                .filter(val -> val.value.equals(input))
                .findFirst()
                .orElse(Color.NONE);
    }
}
