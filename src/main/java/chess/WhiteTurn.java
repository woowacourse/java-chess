package chess;

import chess.chessboard.Side;

public class WhiteTurn extends Turn {

    public WhiteTurn() {
        super(Side.WHITE);
    }

    @Override
    public Turn nextTurn() {
        return new BlackTurn();
    }

    @Override
    public boolean isTurnOf(final Side side) {
        return side == Side.WHITE;
    }
}
