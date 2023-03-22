package chess;

import chess.chessboard.Side;

public class BlackTurn extends Turn {

    public BlackTurn() {
        super(Side.BLACK);
    }

    @Override
    public Turn nextTurn() {
        return new WhiteTurn();
    }

    @Override
    public boolean isTurnOf(final Side side) {
        return side == Side.BLACK;
    }
}
