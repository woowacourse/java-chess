package chess.domain.piece;

import static chess.domain.piece.Symbol.ROOK;

import chess.domain.strategy.RookMoveStrategy;

public final class Rook extends Piece {
    private static final int ROOK_SCORE = 5;

    public Rook(Team team) {
        super(team, ROOK, new RookMoveStrategy());
    }

    @Override
    public double getScore() {
        return ROOK_SCORE;
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
