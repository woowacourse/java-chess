package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

public final class King extends GeneralPiece {

    private static final String INITIAL_NAME = "K";
    private static final double SCORE = 0;

    public King(final Team team) {
        super(team, INITIAL_NAME);
    }

    @Override
    protected List<Direction> createPossibleDirections() {
        return Arrays.asList(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH,
            Direction.NORTHEAST, Direction.NORTHWEST, Direction.SOUTHEAST, Direction.SOUTHWEST);
    }

    @Override
    public boolean hasMiddlePath() {
        return false;
    }

    @Override
    public double score() {
        return SCORE;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
