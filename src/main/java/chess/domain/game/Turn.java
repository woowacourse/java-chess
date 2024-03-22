package chess.domain.game;

import chess.domain.pieces.piece.Color;

public class Turn {
    private Color turn;

    public Turn() {
        turn = Color.WHITE;
    }

    public void next() {
        turn = turn.reverse();
    }

    public boolean isTurn(final Color color) {
        return this.turn == color;
    }
}
