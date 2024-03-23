package domain.piece;

import domain.Direction;
import domain.Square;
import domain.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bishop extends Piece {
    public Bishop(final Team color) {
        super(color);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final List<Square> movableSquares = new ArrayList<>();
        Square northEastSource = source;
        Square northWestSource = source;
        Square southEastSource = source;
        Square southWestSource = source;

        while (northEastSource.canMove(Direction.NORTH_EAST)) {
            northEastSource = northEastSource.next2(Direction.NORTH_EAST);
            movableSquares.add(northEastSource);
        }
        while (northWestSource.canMove(Direction.NORTH_WEST)) {
            northWestSource = northWestSource.next2(Direction.NORTH_WEST);
            movableSquares.add(northWestSource);
        }
        while (southEastSource.canMove(Direction.SOUTH_EAST)) {
            southEastSource = southEastSource.next2(Direction.SOUTH_EAST);
            movableSquares.add(southEastSource);
        }
        while (southWestSource.canMove(Direction.SOUTH_WEST)) {
            southWestSource = southWestSource.next2(Direction.SOUTH_WEST);
            movableSquares.add(southWestSource);
        }
        return movableSquares.contains(target);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Bishop piece)) {
            return false;
        }
        return this.team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, Bishop.class);
    }
}
