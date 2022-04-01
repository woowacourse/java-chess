package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Distance;

public final class Rook extends Piece {
    public Rook(Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public boolean movable(Distance distance, Piece target) {
        return (distance.isHorizontal() || distance.isVertical()) && isOpponent(target);
    }
}
