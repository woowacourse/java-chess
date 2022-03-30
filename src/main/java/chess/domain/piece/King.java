package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Distance;

public final class King extends Piece {
    public King(Color color) {
        super(color, PieceType.KING);
    }

    @Override
    public boolean movable(Distance distance, Piece target) {
        return distance.isMoveOneSpace() && isOpponent(target);
    }
}
