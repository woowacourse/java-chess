package domain.piece.piecerole;

import static domain.move.Direction.EAST;
import static domain.move.Direction.NORTH;
import static domain.move.Direction.NORTH_EAST;
import static domain.move.Direction.NORTH_WEST;
import static domain.move.Direction.SOUTH;
import static domain.move.Direction.SOUTH_EAST;
import static domain.move.Direction.SOUTH_WEST;
import static domain.move.Direction.WEST;

import domain.move.Movable;
import domain.position.Position;
import java.util.List;
import java.util.Objects;

public class Queen implements PieceRole {
    private static final int MAX_MOVEMENT = 7;

    private final List<Movable> routes;

    public Queen() {
        routes = List.of(
                new Movable(MAX_MOVEMENT, NORTH),
                new Movable(MAX_MOVEMENT, EAST),
                new Movable(MAX_MOVEMENT, SOUTH),
                new Movable(MAX_MOVEMENT, WEST),
                new Movable(MAX_MOVEMENT, NORTH_EAST),
                new Movable(MAX_MOVEMENT, NORTH_WEST),
                new Movable(MAX_MOVEMENT, SOUTH_EAST),
                new Movable(MAX_MOVEMENT, SOUTH_WEST)

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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Queen queen = (Queen) o;
        return Objects.equals(routes, queen.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routes);
    }
}
