package chess.domain.piece;

import chess.domain.position.Position;

public class Queen extends Piece {

    private static final double QUEEN_SCORE = 9;

    public Queen(Color color) {
        super(color, QUEEN_SCORE);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return fromPosition.isDiagonal(toPosition) || fromPosition.isCross(toPosition);
    }
}
