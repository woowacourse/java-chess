package domain.piece;

import domain.Section;
import domain.type.Color;
import domain.type.PieceType;

public class Rook extends SlidingPiece {

    private Rook(final Color color) {
        super(color, PieceType.ROOK);
    }

    public static Rook makeBlack() {
        return new Rook(Color.BLACK);
    }

    public static Rook makeWhite() {
        return new Rook(Color.WHITE);
    }

    @Override
    protected boolean isNotMovable(final Section start, final Section end) {
        return start.isNotSameLine(end) || start.isSameColor(end);
    }
}
