package domain.piece.nonpawn;

import domain.piece.Color;
import domain.piece.Type;
import domain.position.Position;

public class Queen extends NonPawnPiece {
    public Queen(final Color color) {
        super(color);
    }

    @Override
    protected void validateDirection(final Position source, final Position target) {
        if (!source.isDiagonalAt(target) && !source.isStraightAt(target)) {
            throw new IllegalArgumentException("퀸은 대각선, 수평, 수직 방향으로만 이동할 수 있습니다.");
        }
    }

    @Override
    public Type type() {
        return Type.QUEEN;
    }
}
