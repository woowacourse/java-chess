package domain.piece.piecerole;

import static domain.game.Direction.DOWN_LEFT;
import static domain.game.Direction.DOWN_RIGHT;
import static domain.game.Direction.LEFT_DOWN;
import static domain.game.Direction.LEFT_UP;
import static domain.game.Direction.RIGHT_DOWN;
import static domain.game.Direction.RIGHT_UP;
import static domain.game.Direction.UP_LEFT;
import static domain.game.Direction.UP_RIGHT;

import domain.game.Movable;
import domain.piece.Piece;
import domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Knight extends PieceRole {
    private static final int MAX_MOVEMENT = 1;

    private Knight(final List<Movable> routes) {
        super(routes);
    }

    public static Knight create() {
        List<Movable> routes = List.of(
                new Movable(MAX_MOVEMENT, DOWN_LEFT),
                new Movable(MAX_MOVEMENT, DOWN_RIGHT),
                new Movable(MAX_MOVEMENT, LEFT_DOWN),
                new Movable(MAX_MOVEMENT, LEFT_UP),
                new Movable(MAX_MOVEMENT, RIGHT_DOWN),
                new Movable(MAX_MOVEMENT, RIGHT_UP),
                new Movable(MAX_MOVEMENT, UP_LEFT),
                new Movable(MAX_MOVEMENT, UP_RIGHT)
        );
        return new Knight(routes);
    }

    @Override
    public void validateMovableRoute(final Position source, final Position target,
                                     final Map<Position, Piece> chessBoard) {
        validateCorrectRouteForPiece(source, target);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Knight knight = (Knight) o;
        return Objects.equals(routes, knight.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routes);
    }
}
