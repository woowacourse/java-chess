package chess.domain.piece;

import java.util.Arrays;

public enum Color {

    WHITE("white"),
    BLACK("black"),
    NONE("없음");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public boolean isSameColor(Color color) {
        return this == color;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public Color nextTurn() {
        if (this.isWhite()) {
            return BLACK;
        }
        return WHITE;
    }

    public static Color of(String name) {
        return Arrays.stream(values())
            .filter(x -> x.name.equals(name))
            .findFirst()
            .orElse(NONE);
    }

    public String getName() {
        return name;
    }
}


