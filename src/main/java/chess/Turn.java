package chess;

import chess.chessboard.Side;
import chess.piece.Piece;

public abstract class Turn {

    private final Side side;

    Turn(final Side side) {
        this.side = side;
    }

    public static Turn initialTurn() {
        return new WhiteTurn();
    }

    public abstract Turn nextTurn();

    public abstract boolean isTurnOf(final Piece side);
}
