package domain.piece;

import domain.Direction;
import domain.Square;
import domain.Team;

import java.util.List;

public abstract class Piece {
    protected final Team team;

    protected Piece(final Team team) {
        this.team = team;
    }


    protected static void addMovableSquares(final Square source, final Direction direction, final List<Square> movableSquares) {
        Square movableSource = source;
        while (movableSource.canMove(direction)) {
            movableSource = movableSource.next2(direction);
            movableSquares.add(movableSource);
        }
    }

    public boolean canNotMove(final Square source, final Square target) {
        return !canMove(source, target);
    }

    protected abstract boolean canMove(Square source, Square target);

    public boolean canNotAttack(final Square source, final Square target) {
        return !canAttack(source, target);
    }

    protected boolean canAttack(final Square source, final Square target) {
        return canMove(source, target);
    }

    public boolean isSameTeam(final Piece other) {
        return this.team == other.team;
    }

    public boolean isOppositeTeam(final Team other) {
        return team != other;
    }

    public abstract boolean equals(final Object o);

    public abstract int hashCode();
}
