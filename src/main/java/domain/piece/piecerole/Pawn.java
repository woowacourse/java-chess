package domain.piece.piecerole;

import domain.game.Direction;
import domain.game.Movable;
import domain.game.Square;
import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Pawn extends PieceRole {
    private static final int INITIAL_MAX_MOVEMENT = 2;

    private boolean hasNotMoved;

    private Pawn(List<Movable> routes) {
        super(routes);
        this.hasNotMoved = true;
    }

    public static Pawn from(final Color color) {
        if (color == Color.WHITE) {
            List<Movable> routes = List.of(
                    new Movable(INITIAL_MAX_MOVEMENT, Direction.NORTH),
                    new Movable(INITIAL_MAX_MOVEMENT, Direction.NORTH_EAST),
                    new Movable(INITIAL_MAX_MOVEMENT, Direction.NORTH_WEST)
            );
            return new Pawn(routes);
        }
        if (color == Color.BLACK) {
            List<Movable> routes = List.of(
                    new Movable(INITIAL_MAX_MOVEMENT, Direction.SOUTH),
                    new Movable(INITIAL_MAX_MOVEMENT, Direction.SOUTH_EAST),
                    new Movable(INITIAL_MAX_MOVEMENT, Direction.SOUTH_WEST)
            );
            return new Pawn(routes);
        }
        return new Pawn(new ArrayList<>());
    }

    @Override
    public void validateMovableRoute(final Position source, final Position target,
                                     final Map<Square, Piece> chessBoard) {
        validateCorrectRouteForPiece(source, target);
        validateHasAnotherPieceOnTarget(source, target, chessBoard);
        validateBlockedRoute(source, target, chessBoard);
        if (hasNotMoved) {
            routes.forEach(Movable::decreaseMaxMovement);
            hasNotMoved = false;
        }
    }

    private void validateHasAnotherPieceOnTarget(
            final Position source,
            final Position target,
            final Map<Square, Piece> chessBoard) {
        Direction direction = Direction.findDirection(source, target);
        if (direction.isForward()) {
            validateForwardPiece(target, chessBoard);
        }
        if (direction.isDiagonal()) {
            validateDiagonalPiece(target, chessBoard);
        }
    }

    private void validateForwardPiece(final Position target, final Map<Square, Piece> chessBoard) {
        if (chessBoard.containsKey(new Square(target))) {
            throw new IllegalStateException("전진하려는 곳에 다른 기물이 있어서 이동할 수 없습니다.");
        }
    }

    private void validateDiagonalPiece(final Position target, final Map<Square, Piece> chessBoard) {
        if (!chessBoard.containsKey(new Square(target))) {
            throw new IllegalStateException("다른 진영의 기물이 있을 때만 대각선으로 이동할 수 있습니다.");
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
