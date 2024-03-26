package domain.piece.piecerole;

import static domain.move.Direction.E;
import static domain.move.Direction.N;
import static domain.move.Direction.S;
import static domain.move.Direction.W;

import domain.move.Movable;
import domain.position.Position;
import java.util.List;
import java.util.Objects;

public class Rook implements PieceRole {
    private static final int MAX_MOVEMENT = 7;
    private static final List<Movable> ROUTES = List.of(
            new Movable(MAX_MOVEMENT, N),
            new Movable(MAX_MOVEMENT, E),
            new Movable(MAX_MOVEMENT, S),
            new Movable(MAX_MOVEMENT, W)
    );

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
        return true;
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
