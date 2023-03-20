package domain.board;

import domain.square.Camp;

public class Turn {

    private Camp turn;

    public Turn(final Camp turn) {
        this.turn = turn;
    }

    public void invert() {
        if (turn == Camp.BLACK) {
            turn = Camp.WHITE;
            return;
        }
        turn = Camp.BLACK;
    }

    public Camp getTurn() {
        return turn;
    }
}
