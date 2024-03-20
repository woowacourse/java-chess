package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Position;
import domain.piece.info.Type;

public class Pawn extends Piece {
    public Pawn(final Color color, final Type type) {
        super(color, type);
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    public static Pawn black() {
        return new Pawn(Color.BLACK, Type.PAWN);
    }

    public static Pawn white() {
        return new Pawn(Color.WHITE, Type.PAWN);
    }
}
