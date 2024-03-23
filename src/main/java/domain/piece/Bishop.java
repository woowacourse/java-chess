package domain.piece;

import domain.Direction;
import domain.Square;
import domain.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bishop extends Piece {
    private static final List<Direction> movableDirections = List.of(
            Direction.NORTH_EAST,
            Direction.NORTH_WEST,
            Direction.SOUTH_EAST,
            Direction.SOUTH_WEST);

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final List<Square> movableSquares = new ArrayList<>();
        for (final Direction movableDirection : movableDirections) {
            addMovableSquares(source, movableDirection, movableSquares);
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
