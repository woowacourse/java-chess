package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Type;

public class None extends Piece {

    public None(final Color color, final Type type) {
        super(color, type);
    }

    public static None none() {
        return new None(Color.NONE, Type.NONE);
    }

    @Override
    public boolean isMovable() {
        return false;
    }
}
