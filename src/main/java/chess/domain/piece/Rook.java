package chess.domain.piece;

import chess.domain.move.MoveStrategy;
import chess.domain.move.RookMoveStrategy;

public final class Rook extends ValidPiece {

    private static final double POINT = 5;

    public Rook(final Team team) {
        super("ROOK",team, POINT);
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        return new RookMoveStrategy();
    }
}
