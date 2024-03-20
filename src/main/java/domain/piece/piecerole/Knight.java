package domain.piece.piecerole;

import static domain.game.Direction.DOWN_LEFT;
import static domain.game.Direction.DOWN_RIGHT;
import static domain.game.Direction.LEFT_DOWN;
import static domain.game.Direction.LEFT_UP;
import static domain.game.Direction.RIGHT_DOWN;
import static domain.game.Direction.RIGHT_UP;
import static domain.game.Direction.UP_LEFT;
import static domain.game.Direction.UP_RIGHT;

import domain.piece.Movable;
import domain.position.Position;
import java.util.List;
import java.util.Objects;

public class Knight implements PieceRole {
    private final List<Movable> routes;

    public Knight() {
        routes = List.of(
                new Movable(1, DOWN_LEFT),
                new Movable(1, DOWN_RIGHT),
                new Movable(1, LEFT_DOWN),
                new Movable(1, LEFT_UP),
                new Movable(1, RIGHT_DOWN),
                new Movable(1, RIGHT_UP),
                new Movable(1, UP_LEFT),
                new Movable(1, UP_RIGHT)
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
        Knight knight = (Knight) o;
        return Objects.equals(routes, knight.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routes);
    }
}
