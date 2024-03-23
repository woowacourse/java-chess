package domain.piece;

import domain.Direction;
import domain.Square;
import domain.Team;

import java.util.Objects;

public class King extends Piece {

    public King(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        if (source.next2(Direction.NORTH).equals(target)) {
            return true;
        }
        if (source.next2(Direction.SOUTH).equals(target)) {
            return true;
        }
        if (source.next2(Direction.EAST).equals(target)) {
            return true;
        }
        if (source.next2(Direction.WEST).equals(target)) {
            return true;
        }
        if (source.next2(Direction.NORTH_EAST).equals(target)) {
            return true;
        }
        if (source.next2(Direction.NORTH_WEST).equals(target)) {
            return true;
        }
        if (source.next2(Direction.SOUTH_EAST).equals(target)) {
            return true;
        }
        if (source.next2(Direction.SOUTH_WEST).equals(target)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final King piece)) {
            return false;
        }
        return this.team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, King.class);
    }
}
