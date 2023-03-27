package chess.domain;

import chess.domain.piece.Color;

public final class Turn {

    private final Color color;

    public Turn(final Color color) {
        this.color = color;
    }

    public static Turn getFirstTurn() {
        return new Turn(Color.WHITE);
    }

    public static Turn next(final Turn turn) {
        if (turn.color.equals(Color.WHITE)) {
            return new Turn(Color.BLACK);
        }
        return new Turn(Color.WHITE);
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public String getValue() {
        return color.name();
    }
}
