package chess.domain.piece;

import chess.domain.position.Position;

public class Queen extends Piece {

    private static final double QUEEN_SCORE = 9;

    public Queen(final Color color) {
        super(color, QUEEN_SCORE);
    }

    @Override
    public boolean isMovable(final Position source, final Position target) {
        return source.isDiagonal(target) || source.isCross(target);
    }
}
