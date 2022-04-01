package chess.domain.piece;

import chess.domain.position.Position;

public class Rook extends Piece {

    private static final double ROOK_SCORE = 5;

    public Rook(final Color color) {
        super(color, ROOK_SCORE);
    }

    @Override
    public boolean isMovable(final Position source, final Position target) {
        return source.isCross(target);
    }
}
