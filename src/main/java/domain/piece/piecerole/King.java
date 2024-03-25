package domain.piece.piecerole;

import static domain.move.Direction.E;
import static domain.move.Direction.N;
import static domain.move.Direction.S;
import static domain.move.Direction.W;

import domain.move.Movable;
import domain.position.Position;
import java.util.List;
import java.util.Objects;

public class King implements PieceRole {
    private static final int MAX_MOVEMENT = 1;

    private final List<Movable> routes;

    public King() {
        routes = List.of(
                new Movable(MAX_MOVEMENT, N),
                new Movable(MAX_MOVEMENT, E),
                new Movable(MAX_MOVEMENT, S),
                new Movable(MAX_MOVEMENT, W)
        );
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition) {
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
        King king = (King) o;
        return Objects.equals(routes, king.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routes);
    }
}
