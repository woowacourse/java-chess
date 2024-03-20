package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Position;
import domain.piece.info.Type;

public class Rook extends Piece {
    public Rook(final Color color, final Type type) {
        super(color, type);
    }

    public static Rook black() {
        return new Rook(Color.BLACK, Type.ROOK);
    }

    public static Rook white() {
        return new Rook(Color.WHITE, Type.ROOK);
    }

    @Override
    public boolean isMovable() {
        return false;
    }
}
