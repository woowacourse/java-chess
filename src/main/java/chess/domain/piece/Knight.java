package chess.domain.piece;

import chess.domain.position.Position;

public class Knight extends Piece {

    private static final double KNIGHT_SCORE = 2.5;
    private static final int KNIGHT_RANGE_DISTANCE = 5;

    public Knight(final Color color) {
        super(color, KNIGHT_SCORE);
    }

    @Override
    public boolean isMovable(final Position source, final Position target) {
        final int height = source.getRankDifference(target);
        final int width = source.getFileDifference(target);
        return Math.pow(height, 2) + Math.pow(width, 2) == KNIGHT_RANGE_DISTANCE;
    }

    @Override
    public boolean isKnight() {
        return true;
    }
}
