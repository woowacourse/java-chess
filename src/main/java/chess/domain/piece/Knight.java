package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

public class Knight extends GeneralPiece {

    private static final String INITIAL_NAME = "N";
    private static final double SCORE = 2.5;

    public Knight(final Team team) {
        super(team, INITIAL_NAME);
    }

    @Override
    protected List<Direction> createPossibleDirections() {
        return Arrays.asList(Direction.NNE, Direction.NNW, Direction.SSE, Direction.SSW,
            Direction.WWN, Direction.WWS, Direction.EEN, Direction.EES);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
