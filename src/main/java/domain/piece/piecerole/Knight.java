package domain.piece.piecerole;

import static domain.movement.Direction.SSW;
import static domain.movement.Direction.SSE;
import static domain.movement.Direction.WSW;
import static domain.movement.Direction.WNW;
import static domain.movement.Direction.ESE;
import static domain.movement.Direction.ENE;
import static domain.movement.Direction.NNW;
import static domain.movement.Direction.NNE;

import domain.movement.Movable;
import domain.position.Position;
import java.util.List;
import java.util.Objects;

public class Knight implements PieceRole {
    private static final int MAX_MOVEMENT = 1;
    private static final List<Movable> ROUTES = List.of(
            new Movable(MAX_MOVEMENT, SSW),
            new Movable(MAX_MOVEMENT, SSE),
            new Movable(MAX_MOVEMENT, WSW),
            new Movable(MAX_MOVEMENT, WNW),
            new Movable(MAX_MOVEMENT, ESE),
            new Movable(MAX_MOVEMENT, ENE),
            new Movable(MAX_MOVEMENT, NNW),
            new Movable(MAX_MOVEMENT, NNE));

    @Override
    public boolean canMove(final Position sourcePosition, final Position targetPosition) {
        return ROUTES.stream()
                .anyMatch(movable -> movable.canMove(sourcePosition, targetPosition));
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isSlidingPiece() {
        return false;
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
        return Objects.hash(ROUTES);
    }
}
