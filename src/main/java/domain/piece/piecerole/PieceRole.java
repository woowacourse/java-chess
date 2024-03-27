package domain.piece.piecerole;

import domain.game.Direction;
import domain.game.Movable;
import domain.piece.Piece;
import domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class PieceRole {
    protected final List<Movable> routes;

    PieceRole(final List<Movable> routes) {
        this.routes = routes;
    }

    public abstract void validateMovableRoute(
            final Position source,
            final Position target,
            final Map<Position, Piece> chessBoard
    );

    protected void validateBlockedRoute(
            final Position source,
            final Position target,
            final Map<Position, Piece> chessBoard
    ) {
        Direction direction = Direction.findDirection(source, target);
        Position here = source.move(direction);
        while (!here.equals(target)) {
            throwIfPieceAtHere(here, chessBoard);
            here = here.move(direction);
        }
    }

    private void throwIfPieceAtHere(final Position here, final Map<Position, Piece> chessBoard) {
        if (chessBoard.containsKey(new Position(here))) {
            throw new IllegalArgumentException("[ERROR]이동 경로에 다른 기물이 있으면 이동할 수 없습니다.");
        }
    }

    protected void validateCorrectRouteForPiece(final Position source, final Position target) {
        boolean cannotMove = routes.stream()
                .noneMatch(movable -> movable.canMove(source, target));
        if (cannotMove) {
            throw new IllegalArgumentException("[ERROR]이동할 수 없는 경로입니다. 다시 입력해주세요.");
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
        final PieceRole pieceRole = (PieceRole) o;
        return Objects.equals(routes, pieceRole.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routes);
    }
}
