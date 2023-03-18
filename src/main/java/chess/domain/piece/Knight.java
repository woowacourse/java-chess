package chess.domain.piece;

import chess.domain.piece.strategy.KnightStrategy;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(color, new KnightStrategy());
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
