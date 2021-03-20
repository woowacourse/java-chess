package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

public class Rook extends GeneralPiece {

    private static final String INITIAL_NAME = "R";
    private static final double SCORE = 5;

    public Rook(final Team team) {
        super(team, INITIAL_NAME);
    }

    @Override
    protected List<Direction> createPossibleDirections() {
        return Arrays.asList(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH);
    }

    @Override
    public boolean hasMiddlePath() {
        return true;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
