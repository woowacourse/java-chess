package chess.domain.piece;

import chess.Strategy.KnightMoveStrategy;

public class Knight extends Piece {
    private static final String KNIGHT_NAME = "N";
    private static final double SCORE = 2.5;
    private static final String whiteUnicode = "&#9816;";
    private static final String blackUnicode = "&#9822;";

    public Knight(Team team) {
        super(KNIGHT_NAME, whiteUnicode, blackUnicode, team, SCORE, new KnightMoveStrategy());
    }
}
