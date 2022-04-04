package chess.domain.piece;

import chess.domain.move.MoveStrategy;
import chess.domain.move.QueenMoveStrategy;

public final class Queen extends ValidPiece {

    private static final double POINT = 9;

    public Queen(final Team team) {
        super("QUEEN",team, POINT);
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
        return new QueenMoveStrategy();
    }
}
