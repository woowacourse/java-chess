package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Position;
import domain.piece.info.Type;

public class Queen extends Piece {
    public Queen(final Color color, final Type type) {
        super(color, type);
    }

    public static Queen black() {
        return new Queen(Color.BLACK, Type.QUEEN);
    }

    public static Queen white() {
        return new Queen(Color.WHITE, Type.QUEEN);
    }

    @Override
    public boolean isMovable() {
        return false;
    }
}
