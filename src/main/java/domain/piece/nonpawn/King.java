package domain.piece.nonpawn;

import domain.piece.Color;
import domain.piece.Type;
import domain.position.Position;

public class King extends NonPawnPiece {
    private static final int DISTANCE_LIMIT_COUNT = 1;

    public King(Color color) {
        super(color);
    }

    @Override
    protected void validateMovement(final Position resource, final Position target) {
        if (resource.isAdjacentAt(target)) {
            return;
        }

        throw new IllegalArgumentException(String.format("%s은 한 번에 %d칸만 이동할 수 있습니다.",
                this.getClass().getSimpleName(), DISTANCE_LIMIT_COUNT));
    }

    @Override
    public Type getType() {
        return Type.KING;
    }
}
