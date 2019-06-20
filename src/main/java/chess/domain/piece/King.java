package chess.domain.piece;

import chess.domain.AbstractPiece;
import chess.domain.MoveRules;
import chess.domain.Team;

public class King extends AbstractPiece {
    private static final String NAME = "k";
    private static final double SCORE = 0;

    public King(Team team) {
        super(team, MoveRules::king);
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
