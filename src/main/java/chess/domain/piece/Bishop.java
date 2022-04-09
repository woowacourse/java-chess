package chess.domain.piece;

import static chess.domain.piece.Symbol.BISHOP;

import chess.domain.strategy.BishopMoveStrategy;

public final class Bishop extends Piece {
    private static final int BISHOP_SCORE = 3;

    public Bishop(Team team) {
        super(team, BISHOP, new BishopMoveStrategy());
    }

    @Override
    public double getScore() {
        return BISHOP_SCORE;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
