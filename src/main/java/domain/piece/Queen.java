package domain.piece;

import domain.Direction;
import domain.Square;
import domain.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Queen extends Piece {
    public Queen(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final List<Square> movableSquares = new ArrayList<>();
        addMovableSquares(source, Direction.NORTH, movableSquares);
        addMovableSquares(source, Direction.SOUTH, movableSquares);
        addMovableSquares(source, Direction.EAST, movableSquares);
        addMovableSquares(source, Direction.WEST, movableSquares);
        addMovableSquares(source, Direction.NORTH_EAST, movableSquares);
        addMovableSquares(source, Direction.NORTH_WEST, movableSquares);
        addMovableSquares(source, Direction.SOUTH_EAST, movableSquares);
        addMovableSquares(source, Direction.SOUTH_WEST, movableSquares);

        return movableSquares.contains(target);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Queen piece)) {
            return false;
        }
        return this.team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, Queen.class);
    }
}
