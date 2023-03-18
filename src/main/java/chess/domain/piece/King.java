package chess.domain.piece;

import chess.domain.piece.strategy.KingStrategy;

public class King extends Piece {

    public King(final Color color) {
        super(color, new KingStrategy());
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
