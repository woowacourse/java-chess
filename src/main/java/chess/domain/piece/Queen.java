package chess.domain.piece;

import chess.Strategy.QueenMoveStrategy;

public class Queen extends Piece {
    private static final String QUEEN_NAME = "Q";
    private static final double SCORE = 9;
    private static final String whiteUnicode = "&#9813;";
    private static final String blackUnicode = "&#9819;";

    public Queen(Team team) {
        super(QUEEN_NAME, whiteUnicode, blackUnicode, team, SCORE, new QueenMoveStrategy());
    }
}
