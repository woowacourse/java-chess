package chess.domain.piece;

import chess.domain.location.Direction;
import chess.domain.moveStrategy.SingleMove;

public class Knight extends Division {
    public static final double KNIGHT_SCORE = 2.5;

    public Knight(Color color) {
        super(color, KNIGHT_SCORE, Unicode.KNIGHT.of(color), new SingleMove(color, Direction.knight()));
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
