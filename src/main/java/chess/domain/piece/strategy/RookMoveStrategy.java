package chess.domain.piece.strategy;

import chess.domain.board.Square;
import chess.domain.order.MoveOrder;
import chess.domain.piece.Color;
import chess.domain.position.Direction;

import java.util.List;

public class RookMoveStrategy implements MoveStrategy {
    private static List<Direction> movableDirections = Direction.linearDirection();

    @Override
    public boolean canMove(MoveOrder moveOrder) {
        if (!isProperDirection(moveOrder.getDirection())) {
            throw new IllegalArgumentException("움직일 수 없는 방향입니다.");
        }

        validateBlockedRoute(moveOrder.getRoute());

        if (moveOrder.getTo().hasPiece()) {
            validateSameColorPiece(moveOrder);
        }

        return true;
    }

    private void validateBlockedRoute(List<Square> route) {
        boolean blocked = route.stream()
                .anyMatch(Square::hasPiece);

        if (blocked) {
            throw new IllegalArgumentException("중간에 말이 있어 행마할 수 없습니다.");
        }
    }

    private void validateSameColorPiece(MoveOrder moveOrder) {
        Color fromColor = moveOrder.getFrom().getPiece().getColor();
        Color toColor = moveOrder.getTo().getPiece().getColor();

        if (fromColor == toColor) {
            throw new IllegalArgumentException("동일한 진영의 말이 있어서 행마할 수 없습니.");
        }
    }

    private boolean isProperDirection(Direction direction) {
        return movableDirections.contains(direction);
    }
}
