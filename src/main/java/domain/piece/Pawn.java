package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Position;
import domain.piece.info.Type;

public class Pawn extends Piece {
    public Pawn(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public Type shape() {
        return Type.PAWN;
    }

    @Override
    public boolean isMovable() {
        return false;
    }
}
