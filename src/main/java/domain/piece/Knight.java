package domain.piece;

import domain.position.Direction;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

import java.util.List;

public final class Knight extends Piece {

    private static final String NAME = "Knight";
    private static final int ONE_STEP_OF_KNIGHT = 3;

    public Knight(final Team team) {
        super(NAME, team);
    }

    @Override
    public List<Position> getInitialBlackPositions() {
        return List.of(Position.of(File.B, Rank.EIGHT),
                Position.of(File.G, Rank.EIGHT));
    }

    @Override
    public List<Position> getInitialWhitePositions() {
        return List.of(Position.of(File.B, Rank.ONE),
                Position.of(File.G, Rank.ONE));
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        return source.getDistance(destination) == ONE_STEP_OF_KNIGHT &&
                !Direction.isStraight(source, destination) &&
                !Direction.isDiagonal(source, destination);
    }
}
