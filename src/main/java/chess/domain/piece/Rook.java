package chess.domain.piece;

import chess.Strategy.RookMoveStrategy;

public class Rook extends Piece {
    private static final String ROOK_NAME = "R";
    private static final double SCORE = 5;
    private static final String whiteUnicode = "&#9814;";
    private static final String blackUnicode = "&#9820;";

    public Rook(Team team) {
        super(ROOK_NAME, whiteUnicode, blackUnicode, team, SCORE, new RookMoveStrategy());
    }
}
