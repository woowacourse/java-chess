package chess.chessgame;

import chess.chessboard.Side;

public class WhiteTurn implements Turn {

    @Override
    public Turn nextTurn() {
        return new BlackTurn();
    }

    @Override
    public boolean isTurnOf(final Side side) {
        return side == Side.WHITE;
    }

    @Override
    public Side getSide() {
        return Side.WHITE;
    }
}
