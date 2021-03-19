package chess.domain.piece.strategy;

import chess.domain.board.Square;
import chess.domain.order.MoveOrder;
import chess.domain.piece.Color;
import chess.domain.position.Direction;

import java.util.List;

public class PawnMoveStrategy implements MoveStrategy {
    private final List<Direction> movableDirections;
    // TODO static or singleTon
    private final Color color;

    public PawnMoveStrategy(Color color) {
        this.movableDirections = Direction.pawnDirection(color);
        this.color = color;
    }

    @Override
    public boolean canMove(MoveOrder moveOrder) {
        Direction direction = moveOrder.getDirection();
        if (!isProperDirection(direction)) {
            throw new IllegalArgumentException("움직일 수 없는 방향입니다.");
        }

        List<Square> routes = moveOrder.getRoute();
        if (routes.size() != 0) {   // TODO 2칸 이동시의 분기를 구현해야한다.
            throw new IllegalArgumentException("폰이 움직일 수 있는 범위를 벗어났습니다.");
        }
        // TODO 2칸 이동 시 중간에 막고 있는 기물이 없어야한다.

        if (Direction.isDiagonal(direction)) {
            validateKillMove(moveOrder);
            return true;
        }

        if (moveOrder.getTo().hasPiece()) {
            throw new IllegalArgumentException("아군 말이 있어 움직일 수 없습니다.");
        }
        return true;
    }

    private boolean isProperDirection(Direction direction) {
        return movableDirections.contains(direction);
    }

    private void validateKillMove(MoveOrder moveOrder) {
        if (!moveOrder.getTo().hasPiece()) {
            throw new IllegalArgumentException("상대 말을 잡을 때에만 대각선으로 움직일 수 있습니다.");
        }
        if (moveOrder.getTo().getPiece().getColor() == this.color) {
            throw new IllegalArgumentException("아군 말이 있어 대각선으로 움직일 수 없습니다.");
        }
    }
}
