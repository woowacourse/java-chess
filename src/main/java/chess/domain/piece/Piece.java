package chess.domain.piece;

import chess.domain.Side;

public abstract class Piece {
    private final Side side;

    public Piece(final Side side) {
        this.side = side;
    }
}
