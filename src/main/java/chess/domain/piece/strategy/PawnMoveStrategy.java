package chess.domain.piece.strategy;

import chess.domain.order.MoveOrder;
import chess.domain.piece.attribute.Color;
import chess.domain.position.Direction;

public class PawnMoveStrategy extends DefaultMoveStrategy {
    private final Color color;

    public PawnMoveStrategy(Color color) {
        super(Direction.pawnDirection(color));
        this.color = color;
    }

    @Override
    public boolean canMove(MoveOrder moveOrder) {
        if (Direction.isDiagonal(moveOrder.getDirection())) {
            validateKillMove(moveOrder);
            return true;
        }

        if (moveOrder.getRoute().size() != 0) {   // TODO 2칸 이동시의 분기를 구현해야한다.
            throw new IllegalArgumentException("폰이 움직일 수 있는 범위를 벗어났습니다.");
        }

        return super.canMove(moveOrder);
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
