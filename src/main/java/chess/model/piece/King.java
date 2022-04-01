package chess.model.piece;

import chess.model.Team;
import chess.model.position.Direction;
import chess.model.position.Distance;
import chess.model.strategy.LimitedMoveStrategy;

public class King extends Piece {
    private static final String BLACK_NAME = "K";
    private static final String WHITE_NAME = "k";
    private static final double SCORE = 0D;

    public King(Team team) {
        super(team, new LimitedMoveStrategy(Direction.all(), Distance.oneStep()));
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

    @Override
    public boolean isKing() {
        return true;
    }
}
