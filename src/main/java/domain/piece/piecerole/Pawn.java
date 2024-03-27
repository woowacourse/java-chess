package domain.piece.piecerole;

import domain.game.Direction;
import domain.game.Movable;
import domain.piece.Piece;
import domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Pawn extends PieceRole {
    protected static final int INITIAL_MAX_MOVEMENT = 2;
    protected static final int ORIGINAL_MAX_MOVEMENT = 1;

    protected Pawn(final List<Movable> routes) {
        super(routes);
    }

    @Override
    public void validateMovableRoute(final Position source, final Position target,
                                     final Map<Position, Piece> chessBoard) {
        validateCorrectRouteForPiece(source, target);
        validateHasAnotherPieceOnTarget(source, target, chessBoard);
        validateBlockedRoute(source, target, chessBoard);
    }

    @Override
    protected void validateCorrectRouteForPiece(final Position source,
                                                final Position target) {
        List<Movable> movables = generateCurrentMovable(source);
        boolean cannotMove = movables.stream()
                .noneMatch(movable -> movable.canMove(source, target));
        if (cannotMove) {
            throw new IllegalArgumentException("[ERROR]이동할 수 없는 경로입니다. 다시 입력해주세요.");
        }
    }

    protected abstract List<Movable> generateCurrentMovable(final Position source);

    private void validateHasAnotherPieceOnTarget(
            final Position source,
            final Position target,
            final Map<Position, Piece> chessBoard) {
        Direction direction = Direction.findDirection(source, target);
        if (direction.isForward()) {
            validateForwardPiece(target, chessBoard);
        }
        if (direction.isDiagonal()) {
            validateDiagonalPiece(target, chessBoard);
        }
    }

    private void validateForwardPiece(final Position target, final Map<Position, Piece> chessBoard) {
        if (chessBoard.containsKey(new Position(target))) {
            throw new IllegalArgumentException("[ERROR]전진하려는 곳에 다른 기물이 있어서 이동할 수 없습니다.");
        }
    }

    private void validateDiagonalPiece(final Position target, final Map<Position, Piece> chessBoard) {
        if (!chessBoard.containsKey(new Position(target))) {
            throw new IllegalArgumentException("[ERROR]다른 진영의 기물이 있을 때만 대각선으로 이동할 수 있습니다.");
        }
    }

    protected abstract boolean isStartPosition(final Position source);

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
