package chess;

import chess.chessboard.Side;

public interface Turn {

    static Turn initialTurn() {
        return new WhiteTurn();
    }

    Turn nextTurn();

    boolean isTurnOf(final Side side);
}
