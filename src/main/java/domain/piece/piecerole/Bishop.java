package domain.piece.piecerole;

import static domain.game.Direction.NORTH_EAST;
import static domain.game.Direction.NORTH_WEST;
import static domain.game.Direction.SOUTH_EAST;
import static domain.game.Direction.SOUTH_WEST;

import domain.game.Movable;
import domain.piece.Piece;
import domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Bishop extends PieceRole {
    private static final int MAX_MOVEMENT = 7;

    private Bishop(final List<Movable> routes) {
        super(routes);
    }

    public static Bishop create() {
        List<Movable> routes = List.of(
                new Movable(MAX_MOVEMENT, NORTH_WEST),
                new Movable(MAX_MOVEMENT, NORTH_EAST),
                new Movable(MAX_MOVEMENT, SOUTH_EAST),
                new Movable(MAX_MOVEMENT, SOUTH_WEST)
        );
        return new Bishop(routes);
    }

    @Override
    public void validateMovableRoute(final Position source, final Position target,
                                     final Map<Position, Piece> chessBoard) {
        validateCorrectRouteForPiece(source, target);
        validateBlockedRoute(source, target, chessBoard);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bishop bishop = (Bishop) o;
        return Objects.equals(routes, bishop.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routes);
    }
}
