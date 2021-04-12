package chess.domain.piece;

import chess.domain.location.Direction;
import chess.domain.moveStrategy.MultipleMove;

public class Rook extends Division {
    public static final int ROOK_SCORE = 5;

    public Rook(Color color) {
        super(color, ROOK_SCORE, Unicode.ROOK.of(color), new MultipleMove(color, Direction.orthogonal()));
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
