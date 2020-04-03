package chess.domain.game;

import chess.domain.piece.Color;

public enum Turn {
    WHITE(Color.WHITE),
    BLACK(Color.BLACK);

    private final Color color;

    Turn(Color color) {
        this.color = color;
    }

    public static Turn from(Color color) {
        if (color.isWhite()) {
            return WHITE;
        }
        return BLACK;
    }

    public static Turn from(String color) {
        return from(Color.valueOf(color));
    }

    public Turn next() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public Color getColor() {
        return color;
    }
}
