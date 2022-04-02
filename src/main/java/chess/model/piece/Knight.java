package chess.model.piece;

import chess.model.Team;
import chess.model.position.Direction;
import chess.model.position.Distance;
import chess.model.strategy.LimitedMoveStrategy;

public class Knight extends Piece {
    private static final String BLACK_NAME = "N";
    private static final String WHITE_NAME = "n";
    private static final double SCORE = 2.5D;

    public Knight(Team team) {
        super(team, new LimitedMoveStrategy(Direction.knight(), Distance.oneStep()));
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
