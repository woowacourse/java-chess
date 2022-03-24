package chess.domain.piece;

import chess.domain.move.MoveStrategy;
import chess.domain.move.PawnMoveStrategy;

public final class Pawn extends ValidPiece {

    private static final double POINT = 1;

    public Pawn(final Color color) {
        super(color, POINT);
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        return new PawnMoveStrategy();
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
