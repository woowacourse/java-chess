package domain.piece;

import domain.position.Position;

public class Bishop extends NonPawn {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    protected void validateDirection(Position source, Position target) {
        if (!source.isDiagonalDirectionTo(target)) {
            throw new IllegalArgumentException("Bishop은 대각선 방향으로 이동해야 합니다.");
        }
    }

    @Override
    protected void validateMoveCount(Position source, Position target) {
        // 거리 제한 없음
    }

    @Override
    public Type type() {
        return Type.BISHOP;
    }
}
