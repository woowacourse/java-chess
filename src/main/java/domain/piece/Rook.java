package domain.piece;

import domain.Direction;
import domain.Square;
import domain.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rook extends Piece {
    public Rook(final Team color) {
        super(color);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final List<Square> movableSquares = new ArrayList<>();
        Square northSource = source;
        Square southSource = source;
        Square eastSource = source;
        Square westSource = source;

        while (northSource.canMove(Direction.NORTH)) {
            northSource = northSource.next2(Direction.NORTH);
            movableSquares.add(northSource);
        }
        while (southSource.canMove(Direction.SOUTH)) {
            southSource = southSource.next2(Direction.SOUTH);
            movableSquares.add(southSource);
        }
        while (eastSource.canMove(Direction.EAST)) {
            eastSource = eastSource.next2(Direction.EAST);
            movableSquares.add(eastSource);
        }
        while (westSource.canMove(Direction.WEST)) {
            westSource = westSource.next2(Direction.WEST);
            movableSquares.add(westSource);
        }

        return movableSquares.contains(target);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Rook piece)) {
            return false;
        }
        return this.team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, Rook.class);
    }
}
