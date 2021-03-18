package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

public class Rook extends GeneralPiece {
    private static final String INITIAL_NAME = "R";

    public Rook(final boolean isBlack) {
        super(isBlack, INITIAL_NAME);
    }

    @Override
    protected List<Direction> createPossibleDirections() {
        return Arrays.asList(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH);
    }
}
