package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

public final class Queen extends GeneralPiece {

    private static final String INITIAL_NAME = "Q";
    private static final double SCORE = 9;

    public Queen(final Team team) {
        super(team, INITIAL_NAME);
    }

    @Override
    protected List<Direction> createPossibleDirections() {
        return Arrays.asList(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH,
            Direction.NORTHEAST, Direction.NORTHWEST, Direction.SOUTHEAST, Direction.SOUTHWEST);
    }

    @Override
    public boolean hasMiddlePath() {
        return true;
    }

    @Override
    public double score() {
        return SCORE;
    }
}
