package domain.piece.nonpawn;

import domain.piece.Color;
import domain.piece.Type;
import domain.position.Position;

public class Rook extends NonPawnPiece {
    public Rook(Color color) {
        super(color);
    }

    @Override
    protected void validateMovement(Position resource, Position target) {
        if (resource.isStraightAt(target)) {
            return;
        }

        throw new IllegalArgumentException(String.format("%s은 수평, 수직 방향으로만 이동할 수 있습니다.",
                this.getClass().getSimpleName()));
    }

    @Override
    public Type type() {
        return Type.ROOK;
    }
}
