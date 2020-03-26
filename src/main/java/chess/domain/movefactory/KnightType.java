package chess.domain.movefactory;

import chess.domain.Position;

public class KnightType implements MoveType {
    private final Position targetPosition;

    public KnightType(Position targetPosition) {
        this.targetPosition = targetPosition;
    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }
}
