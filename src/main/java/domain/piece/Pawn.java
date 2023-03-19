package domain.piece;

import domain.Location;
import domain.type.Color;
import domain.type.PieceType;

public final class Pawn extends Piece {

    private Pawn(final Color color) {
        super(color, PieceType.PAWN);
    }

    public static Pawn makeBlack() {
        return new Pawn(Color.BLACK);
    }

    public static Pawn makeWhite() {
        return new Pawn(Color.WHITE);
    }

    @Override
    protected boolean isNotMovable(final Location start, final Location end) {
        int rowDiff = end.getRow() - start.getRow();
        if (isWhite()) {
            return rowDiff < 1 || rowDiff > 2;
        }
        return rowDiff > -1 || rowDiff < -2;
    }
}
