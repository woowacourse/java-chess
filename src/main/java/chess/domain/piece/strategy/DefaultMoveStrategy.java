package chess.domain.piece.strategy;

import chess.domain.board.Square;
import chess.domain.order.MoveOrder;
import chess.domain.position.Direction;

import java.util.List;

public abstract class DefaultMoveStrategy implements MoveStrategy {
    private final List<Direction> movableDirections;

    public DefaultMoveStrategy(List<Direction> movableDirections) {
        this.movableDirections = movableDirections;
    }

    @Override
    public boolean canMove(MoveOrder moveOrder) {
        validateProperDirection(moveOrder);
        validateRouteIsNotBlocked(moveOrder.getRoute());
        validateToSquareHasSameColorOfPiece(moveOrder);

        return true;
    }

    private void validateProperDirection(MoveOrder moveOrder) {
        if (!movableDirections.contains(moveOrder.getDirection())) {
            throw new IllegalArgumentException("움직일 수 없는 방향입니다.");
        }
    }

    private void validateRouteIsNotBlocked(List<Square> route) {
        boolean blocked = route.stream()
                .anyMatch(Square::hasPiece);
        if (blocked) {
            throw new IllegalArgumentException("중간에 말이 있어 행마할 수 없습니다.");
        }
    }

    private void validateToSquareHasSameColorOfPiece(MoveOrder moveOrder) {
        if (moveOrder.getToSquare().hasPiece()) {
            validateSameColorPiece(moveOrder);
        }
    }

    // TODO 체이닝 간소화
    private void validateSameColorPiece(MoveOrder moveOrder) {
        if (moveOrder.getFromSquare().getPiece().isSameColor(moveOrder.getToSquare().getPiece())) {
            throw new IllegalArgumentException("동일한 진영의 말이 있어서 행마할 수 없습니다.");
        }
    }
}
