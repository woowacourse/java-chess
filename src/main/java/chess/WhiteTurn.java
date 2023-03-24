package chess;

import chess.chessboard.Side;
import chess.piece.Piece;

public class WhiteTurn extends Turn {

    public WhiteTurn() {
        super(Side.WHITE);
    }

    @Override
    public Turn nextTurn() {
        return new BlackTurn();
    }

    @Override
    public boolean isTurnOf(final Piece piece) {
        return piece.isWhite();
    }
}
