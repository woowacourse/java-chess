package chess.domain.piece;

import chess.domain.location.Direction;
import chess.domain.moveStrategy.MultipleMove;

public class Queen extends Division {
    public static final int QUEEN_SCORE = 9;

    public Queen(Color color) {
        super(color, QUEEN_SCORE, Unicode.QUEEN.of(color), new MultipleMove(color, Direction.octilinear()));
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
