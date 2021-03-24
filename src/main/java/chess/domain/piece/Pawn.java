package chess.domain.piece;

import chess.Strategy.PawnMoveStrategy;

public class Pawn extends Piece {
    private static final String PAWN_NAME = "P";
    private static final double SCORE = 1;

    public Pawn(Team team) {

        super(PAWN_NAME, team, SCORE, new PawnMoveStrategy(team));
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
