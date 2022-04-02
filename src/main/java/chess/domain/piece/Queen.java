package chess.domain.piece;

import static chess.domain.piece.Symbol.QUEEN;

import chess.domain.strategy.QueenMoveStrategy;

public final class Queen extends Piece {
    private static final int QUEEN_SCORE = 9;

    public Queen(Team team) {
        super(team, QUEEN, new QueenMoveStrategy());
    }

    @Override
    public double getScore() {
        return QUEEN_SCORE;
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
