package chess.piece;

import chess.Side;

public abstract class ChessPiece {

    private final Side side;

    public ChessPiece(Side side) {
        this.side = side;
    }
}
