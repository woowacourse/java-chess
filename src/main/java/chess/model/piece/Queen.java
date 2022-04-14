package chess.model.piece;

import chess.model.Team;
import chess.model.position.Direction;
import chess.model.strategy.UnlimitedMoveStrategy;

public class Queen extends Piece {
    private static final String BLACK_NAME = "Q";
    private static final String WHITE_NAME = "q";
    private static final double SCORE = 9D;

    public Queen(Team team) {
        super(team, new UnlimitedMoveStrategy(Direction.all()));
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
