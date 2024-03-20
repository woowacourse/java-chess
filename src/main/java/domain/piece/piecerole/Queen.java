package domain.piece.piecerole;

import static domain.game.Direction.EAST;
import static domain.game.Direction.NORTH;
import static domain.game.Direction.SOUTH;
import static domain.game.Direction.WEST;

import domain.piece.Movable;
import domain.position.Position;
import java.util.List;

public class Queen implements PieceRole {
    private final List<Movable> routes;

    public Queen() {
        routes = List.of(
                new Movable(7, NORTH),
                new Movable(7, EAST),
                new Movable(7, SOUTH),
                new Movable(7, WEST)
        );
    }

    @Override
    public boolean canMove(final Position sourcePosition, final Position targetPosition) {
        return routes.stream()
                .anyMatch(movable -> movable.canMove(sourcePosition, targetPosition));
    }
}
