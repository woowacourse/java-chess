package chess.domain.piece.strategy;

import chess.domain.square.Direction;
import chess.domain.square.Square;

public class PawnMoveStrategy implements MoveStrategy {

    private final PawnDirection moveDirection;

    public PawnMoveStrategy(final PawnDirection moveDirection) {
        this.moveDirection = moveDirection;
    }

    @Override
    public void move(Square current, Square destination) {
        if (isDoublePawnPush(current, destination)) {
            return;
        }
        // TODO: 리팩터링 필요
        for (Direction movableDirection : moveDirection.getMovableDirections()) {
            try {
                Square next = current.move(movableDirection);
                if (destination.equals(next)) {
                    return;
                }
            } catch (IllegalArgumentException ignored) {}
        }
        throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
    }

    private boolean isDoublePawnPush(Square current, Square destination) {
        if (moveDirection.equals(PawnDirection.UPPER)) {
            return isDoublePawnPushWhenUpper(current, destination);
        }
        return isDoublePawnPushWhenLower(current, destination);
    }

    private boolean isDoublePawnPushWhenUpper(final Square current, final Square destination) {
        Square movedOnce = current.move(Direction.UP);
        Square movedTwice = movedOnce.move(Direction.UP);
        return current.isRankTwo() && movedTwice.equals(destination);
    }

    private boolean isDoublePawnPushWhenLower(final Square current, final Square destination) {
        Square movedOnce = current.move(Direction.DOWN);
        Square movedTwice = movedOnce.move(Direction.DOWN);
        return current.isRankSeven() && movedTwice.equals(destination);
    }
}
