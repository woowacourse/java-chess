package domain.piece;

import domain.Section;
import domain.type.Color;
import domain.type.PieceType;

public final class King extends NonSlidingPiece {

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
    protected boolean isNotMovable(final Section start, final Section end) {
        if (start.isSameCol(end)) {
            return Math.abs(start.getRow() - end.getRow()) != 1 && start.isSameColor(end);
        }
        if (start.isSameRow(end)) {
            return Math.abs(start.getColumn() - end.getColumn()) != 1 && start.isSameColor(end);
        }
        if (start.isDiagonal(end)) {
            return Math.abs(start.getColumn() - end.getColumn()) != 1 && start.isSameColor(end);
        }
        return start.isSameColor(end);
    }
}
