package domain.piece.piecerole;

import static domain.game.Direction.NORTH;
import static domain.game.Direction.SOUTH;

import domain.piece.Movable;
import domain.position.Position;
import java.util.List;

public class Pawn implements PieceRole {
    private final List<Movable> routes;

    public Pawn(final List<Movable> routes) {
        this.routes = List.of(
                new Movable(1, NORTH),
                new Movable(1, SOUTH)
        );
    }

    @Override
    public boolean canMove(final Position sourcePosition, final Position targetPosition) {
        return false;
    }
}
