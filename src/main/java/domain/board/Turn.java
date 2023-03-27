package domain.board;

import domain.piece.Color;
import domain.piece.Piece;

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

    public boolean isNotFor(final Piece piece) {
        return piece.getColor() != turn;
    }

    public Color color() {
        return turn;
    }
}
