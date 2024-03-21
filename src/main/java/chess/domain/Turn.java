package chess.domain;

import chess.domain.piece.PieceColor;

public class Turn {
    private PieceColor turn;

    public Turn() {
        this(PieceColor.WHITE);
    }

    public Turn(PieceColor turn) {
        this.turn = turn;
    }

    public void next() {
        if (turn.isWhite()) {
            turn = PieceColor.BLACK;
            return;
        }
        turn = PieceColor.WHITE;
    }

    public boolean isTurn(PieceColor color) {
        return this.turn == color;
    }
}
