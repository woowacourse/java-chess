package chess.domain.piece;

import chess.domain.position.Position;

public class Rook extends Piece {

    private static final double ROOK_SCORE = 5;

    public Rook(Color color) {
        super(color, ROOK_SCORE);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return fromPosition.isCross(toPosition);
    }
}
