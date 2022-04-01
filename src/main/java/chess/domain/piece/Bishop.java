package chess.domain.piece;

import chess.domain.position.Position;

public class Bishop extends Piece {

    private static final double BISHOP_SCORE = 3;

    public Bishop(final Color color) {
        super(color, BISHOP_SCORE);
    }

    @Override
    public boolean isMovable(final Position source, final Position target) {
        return source.isDiagonal(target);
    }
}
