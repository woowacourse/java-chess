package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Distance;

public final class Bishop extends Piece {
    public Bishop(Color color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public boolean movable(Distance distance, Piece target) {
        return distance.isDiagonal() && isOpponent(target);
    }
}
