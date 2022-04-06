package chess.model.piece;

import chess.model.Team;
import chess.model.position.Direction;
import chess.model.strategy.UnlimitedMoveStrategy;

public class Rook extends Piece {
    private static final String BLACK_NAME = "R";
    private static final String WHITE_NAME = "r";
    private static final double SCORE = 5D;

    public Rook(Team team) {
        super(team, new UnlimitedMoveStrategy(Direction.linear()));
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
