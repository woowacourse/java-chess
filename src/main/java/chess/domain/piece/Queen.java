package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Distance;

public final class Queen extends Piece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean movable(Distance distance, Piece target) {
        return isRightMove(distance) && isOpponent(target);
    }

    private boolean isRightMove(Distance distance) {
        return distance.isVertical() || distance.isHorizontal() || distance.isDiagonal();
    }
}
