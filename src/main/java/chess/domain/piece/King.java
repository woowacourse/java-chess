package chess.domain.piece;

import chess.domain.MoveRules;
import chess.domain.Piece;
import chess.domain.Team;

public class King extends Piece {
    private static final String NAME = "k";
    private static final double SCORE = 0;

    public King(Team team) {
        super(team, MoveRules::king);
    }

    @Override
    public boolean isKing() {
        return true;
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
