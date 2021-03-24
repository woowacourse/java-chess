package chess.domain.piece;

import chess.Strategy.KingMoveStrategy;

public class King extends Piece {
    private static final String KING_NAME = "K";
    private static final double SCORE = 0;

    public King(Team team) {
        super(KING_NAME, team, SCORE, new KingMoveStrategy());
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
