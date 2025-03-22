package chess.domain;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

import chess.domain.piece.Color;

public class Turn {

    private static final Color initialTurn = WHITE;

    private Color turn;

    public Turn() {
        this.turn = initialTurn;
    }

    public Color getTurn() {
        return turn;
    }

    public void changeTurn() {
        if (turn == WHITE) {
            turn = BLACK;
            return;
        }
        turn = WHITE;

    }
}
