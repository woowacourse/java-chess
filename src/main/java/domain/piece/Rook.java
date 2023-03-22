package domain.piece;

import domain.position.Direction;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

import java.util.List;

public final class Rook extends Piece {

    private static final String NAME = "R";

    public Rook(final Team team) {
        super(NAME, team);
    }

    @Override
    public List<Position> getInitialBlackPositions() {
        return List.of(Position.of(File.A, Rank.EIGHT),
                Position.of(File.H, Rank.EIGHT));
    }

    @Override
    public List<Position> getInitialWhitePositions() {
        return List.of(Position.of(File.A, Rank.ONE),
                Position.of(File.H, Rank.ONE));
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        return Direction.isStraight(source, destination);
    }
}
