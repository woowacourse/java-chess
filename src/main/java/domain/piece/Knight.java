package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Position;
import domain.piece.info.Type;

public class Knight extends Piece {
    public Knight(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public Type shape() {
        return Type.KNIGHT;
    }

    @Override
    public boolean isMovable() {
        return false;
    }
}
