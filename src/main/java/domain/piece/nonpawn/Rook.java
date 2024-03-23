package domain.piece.nonpawn;

import domain.piece.Color;
import domain.piece.Type;
import domain.position.Position;

public class Rook extends NonPawnPiece {
    public Rook(final Color color) {
        super(color);
    }

    @Override
    protected void validateDirection(final Position source, final Position target) {
        if (!source.isStraightAt(target)) {
            throw new IllegalArgumentException("룩은 수평, 수직 방향으로만 이동할 수 있습니다.");
        }
    }

    @Override
    public Type type() {
        return Type.ROOK;
    }
}
