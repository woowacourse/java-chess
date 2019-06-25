package chess.domain.piece;

import chess.domain.MoveRules;
import chess.domain.Piece;
import chess.domain.Team;

public class Bishop extends Piece {
    private static final String NAME = "b";
    private static final double SCORE = 3;

    public Bishop(Team team) {
        super(team, MoveRules::bishop);
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
