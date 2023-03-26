package domain.piece;

import domain.position.Direction;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

import java.util.List;

public final class Queen extends Piece {

    private static final String NAME = "Queen";
    private static final double SCORE = 9;

    public Queen(final Team team) {
        super(NAME, team, SCORE);
    }

    @Override
    public List<Position> getInitialBlackPositions() {
        return List.of(Position.of(File.D, Rank.EIGHT));
    }

    @Override
    public List<Position> getInitialWhitePositions() {
        return List.of(Position.of(File.D, Rank.ONE));
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        return Direction.isStraight(source, destination) || Direction.isDiagonal(source, destination);
    }
}
