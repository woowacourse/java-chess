package chess.domain.piece;

import chess.domain.move.KnightMoveStrategy;
import chess.domain.move.MoveStrategy;

public final class Knight extends ValidPiece {

    private static final double POINT = 2.5;

    public Knight(final Team team) {
        super("KNIGHT",team, POINT);
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
        return new KnightMoveStrategy();
    }
}
