package domain.board;

import domain.square.Color;

public class Turn {

    private Color turn;

    public Turn(final Color turn) {
        this.turn = turn;
    }

    public void invert() {
        if (turn == Color.BLACK) {
            turn = Color.WHITE;
            return;
        }
        turn = Color.BLACK;
    }

    public boolean isNotFor(final Color color) {
        return turn != color;
    }
}
