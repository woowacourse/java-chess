package domain.chessgame;

import domain.piece.Color;

public final class Turn {

    private final Color color;

    public Turn(final Color color) {
        this.color = color;
    }

    public Turn nextTurn() {
        return new Turn(color.reverse());
    }

    public Color getColor() {
        return color;
    }
}
