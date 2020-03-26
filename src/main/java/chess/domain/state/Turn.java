package chess.domain.state;

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
