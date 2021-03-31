package chess.domain.piece;

import chess.Strategy.BishopMoveStrategy;

public class Bishop extends Piece {
    private static final String BISHOP_NAME = "B";
    private static final double SCORE = 3;
    private static final String whiteUnicode = "&#9815;";
    private static final String blackUnicode = "&#9821;";

    public Bishop(Team team) {
        super(BISHOP_NAME, whiteUnicode, blackUnicode, team, SCORE, new BishopMoveStrategy());
    }
}
