package chess.domain.piece;

import chess.Strategy.PawnMoveStrategy;

public class Pawn extends Piece {
    private static final String PAWN_NAME = "P";
    private static final double SCORE = 1;
    private static final String whiteUnicode = "&#9817;";
    private static final String blackUnicode = "&#9823;";

    public Pawn(Team team) {
        super(PAWN_NAME, whiteUnicode, blackUnicode, team, SCORE, new PawnMoveStrategy(team));
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
