package domain.piece.piecerole;

import static domain.move.Direction.SSW;
import static domain.move.Direction.SSE;
import static domain.move.Direction.WSW;
import static domain.move.Direction.WNW;
import static domain.move.Direction.ESE;
import static domain.move.Direction.ENE;
import static domain.move.Direction.NNW;
import static domain.move.Direction.NNE;

import domain.move.Movable;
import domain.position.Position;
import java.util.List;
import java.util.Objects;

public class Knight implements PieceRole {
    private static final int MAX_MOVEMENT = 1;
    private final List<Movable> routes;

    public Knight() {
        routes = List.of(
                new Movable(MAX_MOVEMENT, SSW),
                new Movable(MAX_MOVEMENT, SSE),
                new Movable(MAX_MOVEMENT, WSW),
                new Movable(MAX_MOVEMENT, WNW),
                new Movable(MAX_MOVEMENT, ESE),
                new Movable(MAX_MOVEMENT, ENE),
                new Movable(MAX_MOVEMENT, NNW),
                new Movable(MAX_MOVEMENT, NNE)
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
