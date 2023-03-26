package chess.chessgame;

import chess.chessboard.Side;

public interface Turn {

    static Turn from(Side side) {
        if (side == Side.BLACK) {
            return new BlackTurn();
        }
        return new WhiteTurn();
    }

    static Turn initialTurn() {
        return new WhiteTurn();
    }

    Turn nextTurn();

    boolean isTurnOf(final Side side);

    Side getSide();
}
