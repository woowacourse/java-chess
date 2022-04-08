package chess.domain.piece;

import chess.domain.move.KingMoveStrategy;
import chess.domain.move.MoveStrategy;

public final class King extends ValidPiece {

    private static final double POINT = 0;
    private static final String NAME = "king";

    public King(final Team team) {
        super(team, NAME, POINT);
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        return new KingMoveStrategy();
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
