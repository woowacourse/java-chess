package domain.piece.nonpawn;

import domain.piece.Color;
import domain.piece.Type;
import domain.position.Position;

public class Bishop extends NonPawnPiece {
    public Bishop(final Color color) {
        super(color);
    }

    @Override
    protected void validateDirection(final Position source, final Position target) {
        if (!source.isDiagonalAt(target)) {
            throw new IllegalArgumentException("비숍은 대각선 방향으로만 이동할 수 있습니다.");
        }
    }

    @Override
    public Type type() {
        return Type.BISHOP;
    }
}
