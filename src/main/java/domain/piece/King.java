package domain.piece;

import domain.Location;
import domain.type.Color;
import domain.type.PieceType;

public final class King extends Piece {

    private King(final Color color) {
        super(color, PieceType.KING);
    }

    public static King makeBlack() {
        return new King(Color.BLACK);
    }

    public static King makeWhite() {
        return new King(Color.WHITE);
    }

    @Override
    protected boolean isNotMovable(final Location start, final Location end) {
        if (start.isSameCol(end)) {
            return Math.abs(start.getRow() - end.getRow()) != 1;
        }
        if (start.isSameRow(end)) {
            return Math.abs(start.getCol() - end.getCol()) != 1;
        }
        if (start.isDiagonal(end)) {
            return Math.abs(start.getCol() - end.getCol()) != 1;
        }
        return false;
    }
}
