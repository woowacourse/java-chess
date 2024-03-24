package domain.piece.nonpawn;

import domain.piece.Color;
import domain.piece.Type;
import domain.position.Position;

public class King extends NonPawnPiece {
    public King(final Color color) {
        super(color);
    }

    @Override
    protected void validateDirection(final Position source, final Position target) {
        if (!source.isAdjacentAt(target)) {
            throw new IllegalArgumentException("킹은 한 번에 1칸만 이동할 수 있습니다.");
        }
    }

    @Override
    public Type type() {
        return Type.KING;
    }
}
