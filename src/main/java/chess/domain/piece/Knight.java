package chess.domain.piece;

import chess.Strategy.KnightMoveStrategy;

public class Knight extends Piece {
    private static final String KNIGHT_NAME = "N";
    private static final double SCORE = 2.5;

    public Knight(Team team) {
        super(KNIGHT_NAME, team, SCORE, new KnightMoveStrategy());
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
