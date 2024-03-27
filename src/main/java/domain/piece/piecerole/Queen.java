package domain.piece.piecerole;

import static domain.game.Direction.EAST;
import static domain.game.Direction.NORTH;
import static domain.game.Direction.NORTH_EAST;
import static domain.game.Direction.NORTH_WEST;
import static domain.game.Direction.SOUTH;
import static domain.game.Direction.SOUTH_EAST;
import static domain.game.Direction.SOUTH_WEST;
import static domain.game.Direction.WEST;

import domain.game.Movable;
import domain.piece.Piece;
import domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Queen extends PieceRole {
    public static final int MAX_MOVEMENT = 7;

    private Queen(final List<Movable> routes) {
        super(routes);
    }

    public static Queen from() {
        List<Movable> routes = List.of(
                new Movable(MAX_MOVEMENT, NORTH),
                new Movable(MAX_MOVEMENT, EAST),
                new Movable(MAX_MOVEMENT, SOUTH),
                new Movable(MAX_MOVEMENT, WEST),
                new Movable(MAX_MOVEMENT, NORTH_WEST),
                new Movable(MAX_MOVEMENT, NORTH_EAST),
                new Movable(MAX_MOVEMENT, SOUTH_EAST),
                new Movable(MAX_MOVEMENT, SOUTH_WEST)
        );
        return new Queen(routes);
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
        Queen queen = (Queen) o;
        return Objects.equals(routes, queen.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routes);
    }
}
