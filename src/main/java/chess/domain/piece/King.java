package chess.domain.piece;

import chess.Strategy.KingMoveStrategy;

public class King extends Piece {
    private static final String KING_NAME = "K";
    private static final double SCORE = 0;
    private static final String whiteUnicode = "&#9812;";
    private static final String blackUnicode = "&#9818;";

    public King(Team team) {
        super(KING_NAME, whiteUnicode, blackUnicode, team, SCORE, new KingMoveStrategy());
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
