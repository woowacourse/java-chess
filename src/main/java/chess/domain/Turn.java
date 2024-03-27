package chess.domain;

import chess.domain.piece.PieceColor;

public class Turn {
    private PieceColor turn;

    private Turn(final PieceColor turn) {
        this.turn = turn;
    }

    public static Turn first() {
        return new Turn(PieceColor.WHITE);
    }

    public void next() {
        turn = turn.reverse();
    }

    public boolean isNotTurnOwner(final PieceColor color) {
        return this.turn != color;
    }
}
