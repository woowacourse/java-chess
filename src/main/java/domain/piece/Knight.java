package domain.piece;

import domain.Direction;
import domain.Square;
import domain.Team;

import java.util.List;
import java.util.Objects;

public class Knight extends Piece {
    private static final List<Direction> movableDirections = List.of(
            Direction.NORTH_NORTH_EAST,
            Direction.NORTH_NORTH_WEST,
            Direction.SOUTH_SOUTH_EAST,
            Direction.SOUTH_SOUTH_WEST,
            Direction.EAST_EAST_NORTH,
            Direction.EAST_EAST_SOUTH,
            Direction.WEST_WEST_NORTH,
            Direction.WEST_WEST_SOUTH
    );

    public Knight(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        for (final Direction movableDirection : movableDirections) {
            if (source.next2(movableDirection).equals(target)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Knight piece)) {
            return false;
        }
        return this.team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, Knight.class);
    }
}
