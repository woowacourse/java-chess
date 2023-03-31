package chess.domain.game;

import chess.domain.piece.Color;

public class Turn {
    private final Color color;

    public Turn(Color color) {
        this.color = color;
    }

    public boolean isNotCurrentTurn(Color color) {
        return this.color.isOpposite(color);
    }

    public Turn next() {
        return new Turn(color.getReverseColor());
    }

    public Color getColor() {
        return color;
    }
}
