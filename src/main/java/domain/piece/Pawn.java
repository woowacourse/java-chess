package domain.piece;

import domain.Direction;
import domain.Rank;
import domain.Square;
import domain.Team;

import java.util.Objects;

public class Pawn extends Piece {
    public Pawn(final Team color) { // TODO: 변수명 변경
        super(color);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        if (this.team == Team.BLACK) {
            return source.next2(Direction.SOUTH).equals(target) ||
                    (source.next2(Direction.SOUTH_SOUTH).equals(target) && source.isRank(Rank.SEVEN));
        }
        return source.next2(Direction.NORTH).equals(target) ||
                (source.next2(Direction.NORTH_NORTH).equals(target) && source.isRank(Rank.TWO));
    }

    @Override
    public boolean canAttack(final Square source, final Square target) {
        if (this.team == Team.BLACK) {
            return source.next2(Direction.SOUTH_EAST).equals(target) ||
                    source.next2(Direction.SOUTH_WEST).equals(target);
        }
        return source.next2(Direction.NORTH_EAST).equals(target) ||
                source.next2(Direction.NORTH_WEST).equals(target);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Pawn piece)) {
            return false;
        }
        return this.team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, Pawn.class);
    }
}
