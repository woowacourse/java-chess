package domain.piece;

import domain.Section;
import domain.type.Color;
import domain.type.PieceType;

public final class Knight extends NonSlidingPiece {

    private Knight(final Color color) {
        super(color, PieceType.KNIGHT);
    }

    public static Knight makeBlack() {
        return new Knight(Color.BLACK);
    }

    public static Knight makeWhite() {
        return new Knight(Color.WHITE);
    }

    @Override
    protected boolean isNotMovable(final Section start, final Section end) {
        if (Math.abs(start.getColumn() - end.getColumn()) == 1) {
            return Math.abs(start.getRow() - end.getRow()) != 2 && start.isDifferentColor(end);
        }
        if (Math.abs(start.getRow() - end.getRow()) == 1) {
            return Math.abs(start.getColumn() - end.getColumn()) != 2 && start.isDifferentColor(end);
        }
        return start.isDifferentColor(end);
    }
}
