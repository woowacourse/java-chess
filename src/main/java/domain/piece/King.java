package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Position;
import domain.piece.info.Type;

public class King extends Piece {
    public King(final Color color, final Type type) {
        super(color, type);
    }

    public static King black() {
        return new King(Color.BLACK, Type.KING);
    }

    public static King white() {
        return new King(Color.WHITE, Type.KING);
    }
    @Override
    public boolean isMovable() {
        return false;
    }
}
