package domain.piece.nonpawn;

import domain.piece.Color;
import domain.piece.Type;
import domain.position.Position;

public class Knight extends NonPawnPiece {
    public Knight(final Color color) {
        super(color);
    }

    @Override
    protected void validateDirection(final Position source, final Position target) {
        if (!source.isKnightPositionAt(target)) {
            throw new IllegalArgumentException("나이트는 L자 방향으로만 이동할 수 있습니다.");
        }
    }

    @Override
    public Type type() {
        return Type.KNIGHT;
    }
}
