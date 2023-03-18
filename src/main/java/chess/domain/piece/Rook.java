package chess.domain.piece;

import chess.domain.piece.strategy.RookStrategy;

public class Rook extends Piece {

    public Rook(final Color color) {
        super(color, new RookStrategy());
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
