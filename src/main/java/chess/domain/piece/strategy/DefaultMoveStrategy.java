package chess.domain.piece.strategy;

import chess.domain.order.MoveRoute;
import chess.domain.position.Direction;
import chess.exception.DomainException;

import java.util.List;

public abstract class DefaultMoveStrategy implements MoveStrategy {
    private final List<Direction> movableDirections;

    public DefaultMoveStrategy(List<Direction> movableDirections) {
        this.movableDirections = movableDirections;
    }

    @Override
    public boolean canMove(MoveRoute moveRoute) {
        validateProperDirection(moveRoute);
        validateRouteIsNotBlocked(moveRoute);
        validateToSquareHasSameColorOfPiece(moveRoute);

        return true;
    }

    private void validateProperDirection(MoveRoute moveRoute) {
        if (!movableDirections.contains(moveRoute.getDirection())) {
            throw new DomainException("움직일 수 없는 방향입니다.");
        }
    }

    private void validateRouteIsNotBlocked(MoveRoute route) {
        if (route.isBlocked()) {
            throw new DomainException("중간에 말이 있어 행마할 수 없습니다.");
        }
    }

    private void validateToSquareHasSameColorOfPiece(MoveRoute moveRoute) {
        if (moveRoute.hasPieceAtToPosition()) {
            validateSameColorPiece(moveRoute);
        }
    }

    // TODO 체이닝 간소화
    private void validateSameColorPiece(MoveRoute moveRoute) {
        if (moveRoute.getPieceAtToPosition().isSameColor(moveRoute.getPieceAtFromPosition())) {
            throw new DomainException("동일한 진영의 말이 있어서 행마할 수 없습니다.");
        }
    }
}
