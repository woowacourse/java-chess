package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Position;
import domain.piece.info.Type;

public class Knight extends Piece {
    public Knight(final Color color, final Type type) {
        super(color, type);
    }

    public static King black() {
        return new King(Color.BLACK, Type.KNIGHT);
    }

    public static King white() {
        return new King(Color.WHITE, Type.KNIGHT);
    }
    @Override
    public boolean isMovable() {
        return false;
    }
}
