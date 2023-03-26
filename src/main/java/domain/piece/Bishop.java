package domain.piece;

import domain.position.Direction;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

import java.util.List;

public final class Bishop extends Piece {

    private static final String NAME = "Bishop";
    private static final double SCORE = 3;

    public Bishop(final Team team) {
        super(NAME, team, SCORE);
    }

    @Override
    public List<Position> getInitialBlackPositions() {
        return List.of(Position.of(File.C, Rank.EIGHT),
                Position.of(File.F, Rank.EIGHT));
    }

    @Override
    public List<Position> getInitialWhitePositions() {
        return List.of(Position.of(File.C, Rank.ONE),
                Position.of(File.F, Rank.ONE));
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        return Direction.isDiagonal(source, destination);
    }
}
