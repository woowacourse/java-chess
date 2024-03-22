package domain.piece.nonpawn;

import domain.piece.Color;
import domain.piece.Type;
import domain.position.Position;

public class Bishop extends NonPawnPiece {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    protected void validateMovement(final Position resource, final Position target) {
        if (resource.isDiagonalAt(target)) {
            return;
        }

        throw new IllegalArgumentException(String.format("%s은 대각선 방향으로만 이동할 수 있습니다.",
                this.getClass().getSimpleName()));
    }

    @Override
    public Type getType() {
        return Type.BISHOP;
    }
}
