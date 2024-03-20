package domain.piece.piecerole;

import static domain.game.Direction.NORTH;
import static domain.game.Direction.SOUTH;

import domain.piece.Movable;
import domain.position.Position;
import java.util.List;
import java.util.Objects;

public class Pawn implements PieceRole {
    private final List<Movable> routes;

    public Pawn() {
        this.routes = List.of(
                new Movable(1, NORTH),
                new Movable(1, SOUTH)
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
        Pawn pawn = (Pawn) o;
        return Objects.equals(routes, pawn.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routes);
    }
}
