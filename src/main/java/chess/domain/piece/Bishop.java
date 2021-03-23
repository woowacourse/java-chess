package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

public final class Bishop extends GeneralPiece {
    private static final String INITIAL_NAME = "B";
    private static final double SCORE = 3;

    public Bishop(final Team team) {
        super(team, INITIAL_NAME);
    }

    @Override
    protected List<Direction> possibleDirections() {
        return Arrays.asList(Direction.NORTHEAST, Direction.NORTHWEST, Direction.SOUTHEAST, Direction.SOUTHWEST);
    }

    @Override
    public double score() {
        return SCORE;
    }

    @Override
    public boolean multipleMovable() {
        return true;
    }
}
