package domain.piece.nonpawn;

import domain.piece.Color;
import domain.piece.Type;
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
    }

    @Override
    public Type type() {
        return Type.BISHOP;
    }
}
