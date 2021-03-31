package chess.domain.piece;

import chess.Strategy.QueenMoveStrategy;

public class Queen extends Piece {
    private static final String QUEEN_NAME = "Q";
    private static final double SCORE = 9;

    public Queen(Team team) {
        super(QUEEN_NAME, team, SCORE, new QueenMoveStrategy());
    }
}
