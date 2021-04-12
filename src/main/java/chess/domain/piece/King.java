package chess.domain.piece;

import chess.domain.location.Direction;
import chess.domain.moveStrategy.SingleMove;

public class King extends Division {
    public static final int KING_SCORE = 0;

    public King(Color color) {
        super(color, KING_SCORE, Unicode.KING.of(color), new SingleMove(color, Direction.octilinear()));
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
