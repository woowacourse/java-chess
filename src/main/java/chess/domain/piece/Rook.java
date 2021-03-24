package chess.domain.piece;

import chess.Strategy.RookMoveStrategy;

public class Rook extends Piece {
    private static final String ROOK_NAME = "R";
    private static final double SCORE = 5;

    public Rook(Team team) {
        super(ROOK_NAME, team, SCORE, new RookMoveStrategy());
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
