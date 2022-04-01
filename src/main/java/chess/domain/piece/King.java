package chess.domain.piece;

import chess.domain.position.Position;

public class King extends Piece {

    private static final double KING_SCORE = 0;
    private static final int RANGE = 1;

    public King(final Color color) {
        super(color, KING_SCORE);
    }

    @Override
    public boolean isMovable(final Position source, final Position target) {
        return isMovableDirection(source, target) && isMovableRange(source, target);
    }

    private boolean isMovableDirection(final Position source, final Position target) {
        return source.isCross(target) || source.isDiagonal(target);
    }

    private boolean isMovableRange(final Position source, final Position target) {
        return Math.abs(source.getFileDifference(target)) == RANGE
            || Math.abs(source.getRankDifference(target)) == RANGE;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
