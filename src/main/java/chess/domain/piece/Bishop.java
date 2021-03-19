package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

public class Bishop extends GeneralPiece {
    private static final String INITIAL_NAME = "B";
    private static final double SCORE = 3;

    public Bishop(final Team team) {
        super(team, INITIAL_NAME);
    }

    @Override
    protected List<Direction> createPossibleDirections() {
        return Arrays.asList(Direction.NORTHEAST, Direction.NORTHWEST, Direction.SOUTHEAST, Direction.SOUTHWEST);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
