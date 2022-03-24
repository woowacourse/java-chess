package chess.domain.game;

import chess.domain.piece.Color;

public class Turn {

    private Color color;

    public Turn() {
        this.color = Color.WHITE;
    }

    public void nextTurn() {
        color = color.oppositeColor();
    }

    public boolean isRightTurn(final Color color) {
        return this.color == color;
    }
}
