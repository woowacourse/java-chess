package domain.piece.piecerole;

import domain.game.Direction;
import domain.game.Movable;
import domain.piece.Color;
import domain.position.Position;
import java.util.List;
import java.util.Objects;

public class Pawn implements PieceRole {
    private List<Movable> routes;
    private int moveCount;

    public Pawn(final Color color) {
        moveCount = 1;
        if (color == Color.BLACK) {
            this.routes = List.of(
                    new Movable(getMaxMovement(moveCount), Direction.SOUTH),
                    new Movable(getMaxMovement(moveCount), Direction.SOUTH_EAST),
                    new Movable(getMaxMovement(moveCount), Direction.SOUTH_WEST)
            );
        }
        if (color == Color.WHITE) {
            this.routes = List.of(
                    new Movable(getMaxMovement(moveCount), Direction.NORTH),
                    new Movable(getMaxMovement(moveCount), Direction.NORTH_EAST),
                    new Movable(getMaxMovement(moveCount), Direction.NORTH_WEST)
            );
        }
    }

    private int getMaxMovement(final int moveCount) {
        if (moveCount == 1) {
            return 2;
        }
        return 1;
    }

    @Override
    public boolean canMove(final Position sourcePosition, final Position targetPosition) {
        boolean canMove = routes.stream()
                .anyMatch(movable -> movable.canMove(sourcePosition, targetPosition));
        if (moveCount == 1) {
            for (Movable movable : routes) {
                movable.decreaseMaxMovement();
            }
            moveCount++;
        }
        return canMove;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Pawn pawn = (Pawn) o;
        return Objects.equals(routes, pawn.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routes);
    }
}
