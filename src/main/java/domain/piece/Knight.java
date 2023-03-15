package domain.piece;

import domain.position.Position;

public final class Knight extends Piece {

    private static final String NAME = "N";
    private static final int KNIGHT_MOVABLE_DISTANCE = 3;

    public Knight(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        return source.getDistance(destination) == KNIGHT_MOVABLE_DISTANCE &&
                !source.isStraight(destination) &&
                !source.isStraight(destination);
    }
}
