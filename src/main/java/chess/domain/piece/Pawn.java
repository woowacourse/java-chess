package chess.domain.piece;

import chess.domain.move.MoveStrategy;
import chess.domain.move.PawnMoveStrategy;

public final class Pawn extends ValidPiece {

    public static final double PENALTY_POINT = 0.5;

    private static final double POINT = 1;

    public Pawn(final Color color) {
        super(color, POINT);
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        return new PawnMoveStrategy();
    }
}
