package chess.domain.piece;

import chess.domain.position.Position;

public class Bishop extends Piece {

    private static final double BISHOP_SCORE = 3;

    public Bishop(Color color) {
        super(color, BISHOP_SCORE);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return fromPosition.isDiagonal(toPosition);
    }
}
