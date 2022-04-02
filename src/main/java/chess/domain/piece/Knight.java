package chess.domain.piece;

import static chess.domain.piece.Symbol.KNIGHT;

import chess.domain.strategy.KnightMoveStrategy;

public final class Knight extends Piece {

    private static final double KNIGHT_SCORE = 2.5;

    public Knight(Team team) {
        super(team, KNIGHT, new KnightMoveStrategy());
    }

    @Override
    public double getScore() {
        return KNIGHT_SCORE;
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
