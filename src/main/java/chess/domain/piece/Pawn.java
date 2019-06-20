package chess.domain.piece;

import chess.domain.AbstractPiece;
import chess.domain.MoveRules;
import chess.domain.Team;

public class Pawn extends AbstractPiece {
    private static final String NAME = "p";
    private static final int SCORE = 1;

    public Pawn(Team team) {
        super(team, MoveRules::pawn);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
