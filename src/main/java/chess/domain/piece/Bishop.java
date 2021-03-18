package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

public class Bishop extends GeneralPiece {
    private static final String INITIAL_NAME = "B";

    public Bishop(final boolean isBlack) {
        super(isBlack, INITIAL_NAME);
    }

    @Override
    protected List<Direction> createPossibleDirections() {
        return Arrays.asList(Direction.NORTHEAST, Direction.NORTHWEST, Direction.SOUTHEAST, Direction.SOUTHWEST);
    }
}
