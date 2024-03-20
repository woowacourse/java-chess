package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Position;
import domain.piece.info.Type;

public class Bishop extends Piece {
    public Bishop(final Color color, final Type type) {
        super(color, type);
    }


    public static Bishop black() {
        return new Bishop(Color.BLACK, Type.BISHOP);
    }

    public static Bishop white() {
        return new Bishop(Color.WHITE, Type.BISHOP);
    }

    @Override
    public boolean isMovable() {
        return false;
    }
}
