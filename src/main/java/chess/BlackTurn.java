package chess;

import chess.chessboard.Side;
import chess.piece.Piece;

public class BlackTurn extends Turn {

    public BlackTurn() {
        super(Side.BLACK);
    }

    @Override
    public Turn nextTurn() {
        return new WhiteTurn();
    }

    @Override
    public boolean isTurnOf(final Piece piece) {
        return piece.isBlack();
    }
}
