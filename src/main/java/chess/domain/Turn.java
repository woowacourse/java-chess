package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class Turn {

    private Color turn;

    public Turn(final Color turn) {
        this.turn = turn;
    }

    public void change() {
        this.turn = turn.getOtherColor();
    }

    public boolean myTurn(final Piece piece) {
        return turn.equals(piece.getColor());
    }

    public Color getTurn() {
        return turn;
    }
}
