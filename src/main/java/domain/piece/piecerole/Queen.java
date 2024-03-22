package domain.piece.piecerole;

import static domain.game.Direction.EAST;
import static domain.game.Direction.NORTH;
import static domain.game.Direction.NORTH_EAST;
import static domain.game.Direction.NORTH_WEST;
import static domain.game.Direction.SOUTH;
import static domain.game.Direction.SOUTH_EAST;
import static domain.game.Direction.SOUTH_WEST;
import static domain.game.Direction.WEST;

import domain.game.Movable;
import domain.position.Position;
import java.util.List;
import java.util.Objects;

public class Queen implements PieceRole {
    public static final int MAX_MOVEMENT = 7;

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
