package chess.domain.piece;

import chess.domain.move.MoveStrategy;
import chess.domain.move.QueenMoveStrategy;

public final class Queen extends ValidPiece {

    private static final double POINT = 9;
    private static final String NAME = "queen";

    public Queen(final Team team) {
        super(team, NAME, POINT);
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        return new QueenMoveStrategy();
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
