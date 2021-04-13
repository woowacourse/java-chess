package chess.domain.piece.strategy;

import chess.domain.order.MoveRoute;
import chess.domain.position.Direction;
import chess.exception.DomainException;

public class KingMoveStrategy extends DefaultMoveStrategy {
    public static final int KING_MOVABLE_ROUTE_LENGTH = 2;

    public KingMoveStrategy() {
        super(Direction.everyDirection());
    }

    @Override
    public boolean canMove(MoveRoute moveRoute) {
        if (moveRoute.length() > KING_MOVABLE_ROUTE_LENGTH) {
            throw new DomainException("킹이 움직일 수 있는 범위를 벗어났습니다.");
        }
        return super.canMove(moveRoute);
    }
}
