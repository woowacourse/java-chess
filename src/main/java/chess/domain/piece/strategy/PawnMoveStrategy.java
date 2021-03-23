package chess.domain.piece.strategy;

import chess.domain.order.MoveOrder;
import chess.domain.piece.Color;
import chess.domain.position.Direction;
import chess.domain.position.Rank;

public class PawnMoveStrategy extends DefaultMoveStrategy {
    private final Color color;

    public PawnMoveStrategy(Color color) {
        super(Direction.pawnDirection(color));
        this.color = color;
    }

    // TODO 분기 리팩토링
    // TODO 디미터의 법칙
    @Override
    public boolean canMove(MoveOrder moveOrder) {
        if (Direction.isDiagonal(moveOrder.getDirection())) {
            validateKillMove(moveOrder);
            return true;
        }

        if (moveOrder.getRoute().size() > 1) {
            throw new IllegalArgumentException("폰이 움직일 수 있는 범위를 벗어났습니다.");
        }

        if (moveOrder.getRoute().size() == 1) {
            validateFirstMove(moveOrder);
        }

        return super.canMove(moveOrder);
    }

    private void validateKillMove(MoveOrder moveOrder) {
        if (!moveOrder.getToSquare().hasPiece()) {
            throw new IllegalArgumentException("상대 말을 잡을 때에만 대각선으로 움직일 수 있습니다.");
        }
        if (moveOrder.getToSquare().getPiece().isSameColor(this.color)) {
            throw new IllegalArgumentException("아군 말이 있어 대각선으로 움직일 수 없습니다.");
        }
    }

    private void validateFirstMove(MoveOrder moveOrder) {
        if (this.color == Color.WHITE) {
            validateFirstMoveOfWhite(moveOrder);
            return;
        }
        validateFirstMoveOfBlack(moveOrder);
    }

    private void validateFirstMoveOfWhite(MoveOrder moveOrder) {
        if (!moveOrder.getFromPosition().isRankOf(Rank.TWO)) {
            throw new IllegalArgumentException("폰은 첫 행마가 아니라면 2칸 전진할 수 없습니다.");
        }
    }

    private void validateFirstMoveOfBlack(MoveOrder moveOrder) {
        if (!moveOrder.getFromPosition().isRankOf(Rank.SEVEN)) {
            throw new IllegalArgumentException("폰은 첫 행마가 아니라면 2칸 전진할 수 없습니다.");
        }
    }
}
