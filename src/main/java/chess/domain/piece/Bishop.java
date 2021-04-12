package chess.domain.piece;

import chess.domain.location.Direction;
import chess.domain.moveStrategy.MultipleMove;

public class Bishop extends Division {
    public static final int BISHOP_SCORE = 3;

    public Bishop(Color color) {
        super(color, BISHOP_SCORE, Unicode.BISHOP.of(color), new MultipleMove(color, Direction.diagonal()));
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

}
