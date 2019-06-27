package chess.domain.piece;

import chess.domain.MoveRules;
import chess.domain.Team;

public class Queen extends AbstractPiece {
    private static final String NAME = "q";
    private static final double SCORE = 9;

    public Queen(Team team) {
        super(team, MoveRules::queen);
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
