package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Position;
import domain.piece.info.Type;

public class Bishop extends Piece {
    public Bishop(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public Type shape() {
        return Type.BISHOP;
    }

    @Override
    public boolean isMovable() {
        return false;
    }
}
