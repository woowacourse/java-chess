package chess.domain.piece;

import chess.domain.piece.strategy.BishopStrategy;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        super(color, new BishopStrategy());
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
