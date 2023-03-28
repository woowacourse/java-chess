package domain.piece;

import domain.Section;
import domain.type.Color;
import domain.type.PieceType;

public final class Bishop extends SlidingPiece {

    private Bishop(final Color color) {
        super(color, PieceType.BISHOP);
    }

    public static Bishop makeBlack() {
        return new Bishop(Color.BLACK);
    }

    public static Bishop makeWhite() {
        return new Bishop(Color.WHITE);
    }

    @Override
    protected boolean isNotMovable(final Section start, final Section end) {
        return start.isNotDiagonal(end) || start.isSameColor(end);
    }
}
