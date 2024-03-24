package domain.board;

import domain.piece.Color;

public class Turn {
    private Color color;

    private Turn(final Color color) {
        this.color = color;
    }

    Turn() {
        this(Color.WHITE);
    }

    void flip() {
        if (this.color.isBlack()) {
            this.color = Color.WHITE;
            return;
        }
        this.color = Color.BLACK;
    }

    boolean isOpponentTurn(final Color color) {
        return this.color != color && !color.isNeutrality();
    }
}
