package chess.model.piece;

import chess.model.Team;
import chess.model.position.Direction;
import chess.model.strategy.UnlimitedMoveStrategy;

public class Bishop extends Piece {
    private static final String BLACK_NAME = "B";
    private static final String WHITE_NAME = "b";
    private static final double SCORE = 3D;

    public Bishop(Team team) {
        super(team, new UnlimitedMoveStrategy(Direction.diagonal()));
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public String getName() {
        if (Team.BLACK.equals(team)) {
            return BLACK_NAME;
        }
        return WHITE_NAME;
    }
}
