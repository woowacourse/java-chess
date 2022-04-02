package chess.domain.piece;

import static chess.domain.piece.Symbol.PAWN;

import chess.domain.strategy.PawnMoveStrategy;

public class Pawn extends Piece {
    private static final int PAWN_SCORE = 1;

    public Pawn(Team team) {
        super(team, PAWN, new PawnMoveStrategy());
    }

    @Override
    public double getScore() {
        return PAWN_SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
