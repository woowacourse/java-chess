package domain.piece;

import domain.position.Direction;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

import java.util.List;

public final class King extends Piece {

    private static final String NAME = "K";
    private static final int DIAGONAL_ONE_STEP = 2;
    private static final int ONE_STEP = 1;

    public King(final Team team) {
        super(NAME, team);
    }

    @Override
    public List<Position> getInitialBlackPositions() {
        return List.of(Position.of(File.E, Rank.EIGHT));
    }

    @Override
    public List<Position> getInitialWhitePositions() {
        return List.of(Position.of(File.E, Rank.ONE));
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        if (Direction.isDiagonal(source, destination) && source.getDistance(destination) == DIAGONAL_ONE_STEP) {
            return true;
        }

        return Direction.isStraight(source, destination) && source.getDistance(destination) == ONE_STEP;
    }
}
