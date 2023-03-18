package domain.chessgame;

import domain.piece.Color;

public final class Turn {

    private Color color;

    public Turn(final Color color) {
        this.color = color;
    }

    public void nextTurn() {
        color = color.reverse();
    }

    public Color getColor() {
        return color;
    }
}
