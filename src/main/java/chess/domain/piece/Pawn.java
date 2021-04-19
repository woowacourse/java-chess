package chess.domain.piece;

import chess.domain.moveStrategy.PawnMove;

public class Pawn extends Division {
    public static final int PAWN_SCORE = 1;

    public Pawn(Color color) {
        super(color, PAWN_SCORE, Unicode.PAWN.of(color), new PawnMove(color));
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
