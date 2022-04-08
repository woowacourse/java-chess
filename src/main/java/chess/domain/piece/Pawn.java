package chess.domain.piece;

import chess.domain.move.BlackPawnMoveStrategy;
import chess.domain.move.MoveStrategy;
import chess.domain.move.WhitePawnMoveStrategy;

public final class Pawn extends ValidPiece {

    public static final double PENALTY_POINT = 0.5;

    private static final double POINT = 1;
    private static final String NAME = "pawn";

    public Pawn(final Team team) {
        super(team, NAME, POINT);
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        if (getTeam() == Team.BLACK) {
            return new BlackPawnMoveStrategy();
        }
        return new WhitePawnMoveStrategy();
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
