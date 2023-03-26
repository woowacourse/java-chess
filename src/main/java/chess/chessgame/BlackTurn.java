package chess.chessgame;

import chess.chessboard.Side;

public class BlackTurn implements Turn {

    @Override
    public Turn nextTurn() {
        return new WhiteTurn();
    }

    @Override
    public boolean isTurnOf(final Side side) {
        return side == Side.BLACK;
    }

    @Override
    public Side getSide() {
        return Side.BLACK;
    }
}
