package chess.domain.piece;

import chess.domain.AbstractPiece;
import chess.domain.MoveRules;
import chess.domain.Team;

public class Knight extends AbstractPiece {
    private static final String NAME = "n";
    public static final double SCORE = 2.5;

    public Knight(Team team) {
        super(team, MoveRules::knight);
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
