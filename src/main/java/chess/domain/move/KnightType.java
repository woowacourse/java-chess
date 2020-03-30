package chess.domain.move;

import chess.domain.Position;

public class KnightType implements MoveType {
    private final Position targetPosition;

    KnightType(Position targetPosition) {
        this.targetPosition = targetPosition;
    }

    @Override
    public Direction getDirection() {
        throw new UnsupportedOperationException("Knight getDirection 에러");
    }

    @Override
    public int getCount() {
        throw new UnsupportedOperationException("Kinght getCount() 에러");
    }

    public Position getTargetPosition() {
        return targetPosition;
    }
}
