package domain.piece.nonpawn;

import domain.piece.Color;
import domain.piece.Type;
import domain.position.Position;

public class King extends NonPawnPiece {
    private static final int DISTANCE_LIMIT_COUNT = 1;

    public King(final Color color) {
        super(color);
    }

    @Override
    protected void validateDirection(final Position source, final Position target) {
        if (!source.isAdjacentAt(target)) {
            throw new IllegalArgumentException(String.format("킹은 한 번에 %d칸만 이동할 수 있습니다.", DISTANCE_LIMIT_COUNT));
        }
    }

    @Override
    public Type type() {
        return Type.KING;
    }
}
