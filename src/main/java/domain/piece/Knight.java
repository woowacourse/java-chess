package domain.piece;

import domain.position.Direction;
import domain.position.Position;

public final class Knight extends Piece {

    private static final String NAME = "N";
    private static final int ONE_STEP_OF_KNIGHT = 3;

    public Knight(final Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        return source.getDistance(destination) == ONE_STEP_OF_KNIGHT &&
                !Direction.isStraight(source, destination) &&
                !Direction.isDiagonal(source, destination);
    }
}
