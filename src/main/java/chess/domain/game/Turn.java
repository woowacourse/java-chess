package chess.domain.game;

import chess.domain.piece.Color;

public class Turn {
    private Color color;

    public Turn(Color color) {
        this.color = color;
    }

    public boolean isNotCurrentTurn(Color color) {
        return this.color.isOpposite(color);
    }

    public void next() {
        this.color = color.getReverseColor();
    }

    public Color getColor() {
        return color;
    }
}
