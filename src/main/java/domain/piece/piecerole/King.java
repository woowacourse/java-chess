package domain.piece.piecerole;

import static domain.game.Direction.EAST;
import static domain.game.Direction.NORTH;
import static domain.game.Direction.SOUTH;
import static domain.game.Direction.WEST;

import domain.piece.Movable;
import domain.position.Position;
import java.util.List;

public class King implements PieceRole {

    private final List<Movable> routes;

    public King() {
        routes = List.of(
                new Movable(1, NORTH),
                new Movable(1, EAST),
                new Movable(1, SOUTH),
                new Movable(1, WEST)
        );
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition) {
        return routes.stream()
                .anyMatch(movable -> movable.canMove(sourcePosition, targetPosition));

    }
}
