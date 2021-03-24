package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

public final class Knight extends GeneralPiece {
    private static final String INITIAL_NAME = "N";
    private static final double SCORE = 2.5;

    public Knight(final Team team) {
        super(team, INITIAL_NAME);
    }

    @Override
    protected List<Direction> possibleDirections() {
        return Arrays.asList(Direction.NORTH_NORTHEAST, Direction.NORTH_NORTHWEST, Direction.SOUTH_SOUTHEAST, Direction.SOUTH_SOUTHWEST,
                Direction.WEST_NORTHWEST, Direction.WEST_SOUTHWEST, Direction.EAST_NORTHEAST, Direction.EAST_SOUTHEAST);
    }

    @Override
    public double score() {
        return SCORE;
    }

    @Override
    public boolean multipleMovable() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
