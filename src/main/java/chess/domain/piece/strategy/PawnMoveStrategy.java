package chess.domain.piece.strategy;

import chess.domain.board.Square;
import chess.domain.order.MoveOrder;
import chess.domain.piece.attribute.Color;
import chess.domain.position.Direction;
import chess.domain.position.Rank;

public class PawnMoveStrategy extends DefaultMoveStrategy {
    private static final int CAN_FORWARD_DOUBLE = 1;
    private static final int CAN_FORWARD_ONE = 0;

    private final Color color;

    public PawnMoveStrategy(Color color) {
        super(Direction.pawnDirection(color));
        this.color = color;
    }

    @Override
    public boolean canMove(MoveOrder moveOrder) {
        if (isDiagonal(moveOrder)) {
            validateKillMove(moveOrder);
            return true;
        }

        if (moveOrder.getRoute().size() > calculateMovableDistance(moveOrder.getFrom())) {
            throw new IllegalArgumentException("폰이 움직일 수 있는 범위를 벗어났습니다.");
        }

        if (moveOrder.getTo().hasPiece()) {
            throw new IllegalArgumentException("폰은 이동하려는 위치에 기물이 있으면 갈 수 없습니다.");
        }
        return super.canMove(moveOrder);
    }

    private boolean isDiagonal(MoveOrder moveOrder) {
        Color color = moveOrder.getFrom().getPiece().getColor();
        if (color == Color.WHITE) {
            return Direction.isNorthDiagonal(moveOrder.getDirection());
        }
        return Direction.isSouthDiagonal(moveOrder.getDirection());
    }

    private int calculateMovableDistance(Square from) {
        if (from.isSameRank(initialRankOfPawn(from.getPiece().getColor()))) {
            return CAN_FORWARD_DOUBLE;
        }
        return CAN_FORWARD_ONE;
    }

    private Rank initialRankOfPawn(Color color) {
        if (color == Color.WHITE) {
            return Rank.TWO;
        }
        return Rank.SEVEN;
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
