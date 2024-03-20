package domain.piece.piecerole;

import static domain.game.Direction.EAST;
import static domain.game.Direction.NORTH;
import static domain.game.Direction.SOUTH;
import static domain.game.Direction.WEST;

import domain.piece.Movable;
import domain.position.Position;
import java.util.List;

public class Rook implements PieceRole {
    private final List<Movable> routes;

    public Rook() {
        routes = List.of(
                new Movable(7, NORTH),
                new Movable(7, EAST),
                new Movable(7, SOUTH),
                new Movable(7, WEST)
        );
    }

    @Override
    public boolean canMove(final Position sourcePosition, final Position targetPosition) {
        return false;
    }
}
