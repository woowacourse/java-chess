package chess.domain.piece;

import static chess.domain.piece.Symbol.KING;

import chess.domain.strategy.KingMoveStrategy;

public final class King extends Piece {
    private static final int KING_SCORE = 0;

    public King(Team team) {
        super(team, KING, new KingMoveStrategy());
    }

    @Override
    public double getScore() {
        return KING_SCORE;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
