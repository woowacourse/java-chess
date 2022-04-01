package chess.model.piece;

import chess.model.Team;
import chess.model.strategy.NotMoveStrategy;

public class Empty extends Piece {

    private static final String NAME = ".";
    private static final double SCORE = 0D;

    public Empty() {
        super(Team.NONE, new NotMoveStrategy());
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
