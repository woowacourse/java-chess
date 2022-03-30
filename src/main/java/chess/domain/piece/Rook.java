package chess.domain.piece;

import chess.domain.Position;

public class Rook extends Piece {

    private static final double ROOK_SCORE = 5;

    public Rook(Color color) {
        super(color, ROOK_SCORE);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return fromPosition.isSameFile(toPosition) || fromPosition.isSameRank(toPosition);
    }

    @Override
    public boolean isCatchable(Position fromPosition, Position toPosition) {
        return isMovable(fromPosition, toPosition);
    }
}
