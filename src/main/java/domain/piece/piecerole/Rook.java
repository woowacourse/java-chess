package domain.piece.piecerole;

import static domain.move.Direction.EAST;
import static domain.move.Direction.NORTH;
import static domain.move.Direction.SOUTH;
import static domain.move.Direction.WEST;

import domain.move.Movable;
import domain.position.Position;
import java.util.List;
import java.util.Objects;

public class Rook implements PieceRole {
    private static final int MAX_MOVEMENT = 7;

    private final List<Movable> routes;

    public Rook() {
        routes = List.of(
                new Movable(MAX_MOVEMENT, NORTH),
                new Movable(MAX_MOVEMENT, EAST),
                new Movable(MAX_MOVEMENT, SOUTH),
                new Movable(MAX_MOVEMENT, WEST)
        );
    }

    @Override
    public boolean canMove(final Position sourcePosition, final Position targetPosition) {
        return routes.stream()
                .anyMatch(movable -> movable.canMove(sourcePosition, targetPosition));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(routes);
    }
}
