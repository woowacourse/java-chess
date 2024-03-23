package domain.piece.piecerole;

import static domain.game.Direction.EAST;
import static domain.game.Direction.NORTH;
import static domain.game.Direction.SOUTH;
import static domain.game.Direction.WEST;

import domain.game.Movable;
import domain.game.Square;
import domain.piece.Piece;
import domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class King extends PieceRole {
    public static final int MAX_MOVEMENT = 1;

    private King(final List<Movable> routes) {
        super(routes);
    }

    public static King from() {
        List<Movable> routes = List.of(
                new Movable(MAX_MOVEMENT, NORTH),
                new Movable(MAX_MOVEMENT, EAST),
                new Movable(MAX_MOVEMENT, SOUTH),
                new Movable(MAX_MOVEMENT, WEST)
        );
        return new King(routes);
    }

    @Override
    public boolean validateMovableRoute(Position source, Position target, Map<Square, Piece> chessBoard) {
        return routes.stream()
                .anyMatch(movable -> movable.canMove(source, target));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        King king = (King) o;
        return Objects.equals(routes, king.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routes);
    }
}
