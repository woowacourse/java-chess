package chess.domain;

import java.util.List;

public enum Color {

    BLACK,
    WHITE,
    EMPTY;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public Color opposite() {
        return switch (this) {
            case BLACK -> WHITE;
            case WHITE -> BLACK;
            default -> EMPTY;
        };
    }

    public static List<Color> getGameColors() {
        return List.of(WHITE, BLACK);
    }
}
