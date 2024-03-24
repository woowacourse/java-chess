package domain.piece;

import domain.Direction;
import domain.Square;
import domain.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Piece {
    protected final Team team;

    protected Piece(final Team team) {
        this.team = team;
    }

    protected boolean checkMovable(final Square source, final Square target, final List<Direction> movableDirections, final Map<Square, Piece> pieces) {
        final List<Square> movableSquares = new ArrayList<>();
        for (final Direction movableDirection : movableDirections) {
            addMovableSquares(source, movableDirection, movableSquares, pieces);
        }
        return movableSquares.contains(target);
    }

    protected void addMovableSquares(final Square source, final Direction direction, final List<Square> movableSquares, final Map<Square, Piece> pieces) {
        Square movableSource = source;
        while (movableSource.canMove(direction)) {
            movableSource = movableSource.next(direction);
            if (pieces.containsKey(movableSource)) {
                break;
            }
            movableSquares.add(movableSource);
        }
    }

    public boolean canNotMove(final Square source, final Square target, final Map<Square, Piece> pieces) {
        return !canMove(source, target, pieces);
    }

    protected abstract boolean canMove(Square source, Square target, Map<Square, Piece> pieces);

    public boolean canNotAttack(final Square source, final Square target, final Map<Square, Piece> pieces) {
        return !canAttack(source, target, pieces);
    }

    protected boolean canAttack(final Square source, final Square target, final Map<Square, Piece> pieces) {
        return canMove(source, target, pieces);
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
