package chess.domain.game;

import chess.domain.piece.Color;

public class Turn {
    private Color color;

    public Turn() {
        this.color = Color.WHITE;
    }

    public boolean isNotCurrentTurn(Color color) {
        return this.color != color;
    }

    public void next() {
        this.color = color.getReverseColor();
    }

    public Color getColor() {
        return color;
    }
}
