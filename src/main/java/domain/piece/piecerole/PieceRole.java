package domain.piece.piecerole;

import domain.game.Direction;
import domain.game.Movable;
import domain.game.Square;
import domain.piece.Piece;
import domain.position.Position;
import java.util.List;
import java.util.Map;

public abstract class PieceRole {
    protected final List<Movable> routes;

    PieceRole(final List<Movable> routes) {
        this.routes = routes;
    }

    public abstract void validateMovableRoute(Position source, Position target, Map<Square, Piece> chessBoard);

    protected void validateBlockedRoute(final Position source, final Position target,
                                        final Map<Square, Piece> chessBoard) {
        Direction direction = Direction.findDirection(source, target);
        Position here = source.move(direction);
        while (!here.equals(target)) {
            throwIfPieceAtHere(here, chessBoard);
            here = here.move(direction);
        }
    }

    private void throwIfPieceAtHere(final Position here, final Map<Square, Piece> chessBoard) {
        if (chessBoard.containsKey(new Square(here))) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있으면 이동할 수 없습니다.");
        }
    }

    protected void validateCorrectRouteForPiece(final Position source, final Position target) {
        boolean cannotMove = routes.stream()
                .noneMatch(movable -> movable.canMove(source, target));
        if (cannotMove) {
            throw new IllegalArgumentException("이동할 수 없는 경로입니다. 다시 입력해주세요.");
        }
    }
}
