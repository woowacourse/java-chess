package domain.piece.nonpawn;

import domain.piece.Color;
import domain.piece.Type;
import domain.position.Position;

public class Queen extends NonPawn {
    public Queen(Color color) {
        super(color);
    }

    @Override
    protected void validateDirection(Position source, Position target) {
        if (source.isStraightDirectionTo(target) || source.isDiagonalDirectionTo(target)) {
            return;
        }
        throw new IllegalArgumentException("Queen은 대각선 또는 직선 방향으로 이동해야 합니다.");
    }

    @Override
    protected void validateMoveCount(Position source, Position target) {
    }

    @Override
    public Type type() {
        return Type.QUEEN;
    }
}
