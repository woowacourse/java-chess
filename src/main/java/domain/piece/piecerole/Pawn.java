package domain.piece.piecerole;

import domain.game.Direction;
import domain.game.Movable;
import domain.game.Square;
import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Pawn extends PieceRole {
    private static final int INITIAL_MAX_MOVEMENT = 2;
    private static final Map<Color, List<Movable>> MOVABLE_ROUTES = new HashMap<>();

    static {
        MOVABLE_ROUTES.put(Color.BLACK, List.of(
                new Movable(INITIAL_MAX_MOVEMENT, Direction.SOUTH),
                new Movable(INITIAL_MAX_MOVEMENT, Direction.SOUTH_EAST),
                new Movable(INITIAL_MAX_MOVEMENT, Direction.SOUTH_WEST)
        ));
        MOVABLE_ROUTES.put(Color.WHITE, List.of(
                new Movable(INITIAL_MAX_MOVEMENT, Direction.NORTH),
                new Movable(INITIAL_MAX_MOVEMENT, Direction.NORTH_EAST),
                new Movable(INITIAL_MAX_MOVEMENT, Direction.NORTH_WEST)
        ));
    }

    private boolean hasNotMoved;

    private Pawn(List<Movable> routes) {
        super(routes);
        this.hasNotMoved = true;
    }

    public static Pawn from(final Color color) {
        return new Pawn(MOVABLE_ROUTES.get(color));
    }

    @Override
    public void validateMovableRoute(final Position source, final Position target,
                                     final Map<Square, Piece> chessBoard) {
        validateValidRouteForPiece(source, target);
        validateBlockedRoute(source, target, chessBoard);

        if (hasNotMoved) {
            routes.forEach(Movable::decreaseMaxMovement);
            hasNotMoved = false;
        }
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
