package domain.piece.piecerole;

import static domain.game.Direction.NORTH_EAST;
import static domain.game.Direction.NORTH_WEST;
import static domain.game.Direction.SOUTH_EAST;
import static domain.game.Direction.SOUTH_WEST;

import domain.piece.Movable;
import domain.position.Position;
import java.util.List;

public class Bishop implements PieceRole {
    private final List<Movable> routes;

    public Bishop() {
        routes = List.of(
                new Movable(7, NORTH_EAST),
                new Movable(7, NORTH_WEST),
                new Movable(7, SOUTH_EAST),
                new Movable(7, SOUTH_WEST)
        );
    }

    @Override
    public boolean canMove(final Position sourcePosition, final Position targetPosition) {
        return false;
    }
}
