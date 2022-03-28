package chess.domain.piece;

import chess.domain.Position;

public class Bishop extends Piece {

    private static final double BISHOP_SCORE = 3;

    public Bishop(Color color) {
        super(color, BISHOP_SCORE);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        int height = fromPosition.getRankDifference(toPosition);
        int width = fromPosition.getFileDifference(toPosition);
        return Math.pow(height, 2) == Math.pow(width, 2);
    }
}
