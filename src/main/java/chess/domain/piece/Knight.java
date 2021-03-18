package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

public class Knight extends GeneralPiece {
    private static final String INITIAL_NAME = "N";

    public Knight(final boolean isBlack) {
        super(isBlack, INITIAL_NAME);
    }

    @Override
    protected List<Direction> createPossibleDirections() {
        return Arrays.asList(Direction.NNE, Direction.NNW, Direction.SSE, Direction.SSW,
                Direction.WWN, Direction.WWS, Direction.EEN, Direction.EES);
    }
}
