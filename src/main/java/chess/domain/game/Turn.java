package chess.domain.game;

import chess.domain.piece.Color;

public class Turn {
    private Color color;

    public Turn() {
        this.color = Color.BLACK;
    }

    public void next() {
        if (Color.BLACK.equals(color)) {
            color = Color.WHITE;
            return;
        }
        color = Color.BLACK;
    }

    public Color color() {
        return color;
    }
}
