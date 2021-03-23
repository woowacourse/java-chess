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
        return Arrays.asList(Direction.NNE, Direction.NNW, Direction.SSE, Direction.SSW,
                Direction.WWN, Direction.WWS, Direction.EEN, Direction.EES);
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
