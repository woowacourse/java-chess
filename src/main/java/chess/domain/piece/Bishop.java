package chess.domain.piece;

import chess.Strategy.BishopMoveStrategy;

public class Bishop extends Piece {
    private static final String BISHOP_NAME = "B";
    private static final double SCORE = 3;

    public Bishop(Team team) {
        super(BISHOP_NAME, team, SCORE, new BishopMoveStrategy());
    }
}
