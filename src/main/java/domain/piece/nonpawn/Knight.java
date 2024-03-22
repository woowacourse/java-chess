package domain.piece.nonpawn;

import domain.piece.Color;
import domain.piece.Type;
import domain.position.Position;

public class Knight extends NonPawnPiece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    protected void validateMovement(final Position resource, final Position target) {
        if (resource.isKnightPositionAt(target)) {
            return;
        }

        throw new IllegalArgumentException(String.format("%s은 L자 방향으로만 이동할 수 있습니다.",
                this.getClass().getSimpleName()));
    }

    @Override
    public Type type() {
        return Type.KNIGHT;
    }
}
