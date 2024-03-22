package chess.domain;

import chess.domain.piece.PieceColor;

public class Turn {
    private PieceColor turn;

    public static Turn first() {
        return new Turn(PieceColor.WHITE);
    }

    public Turn(PieceColor turn) {
        this.turn = turn;
    }

    public void next() {
        turn = turn.reverse();
    }

    public boolean hasTurn(PieceColor color) {
        return this.turn == color;
    }
}
