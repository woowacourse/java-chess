package domain.piece;

import domain.Section;
import domain.type.Color;
import domain.type.PieceType;

public class Pawn extends SlidingPiece {

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
    protected boolean isNotMovable(final Section start, final Section end) {
        if (Math.abs(start.getRow() - end.getRow()) > 2) {
            return true;
        }
        final PawnRule pawnRule = PawnRule.find(start, end);
        if (!pawnRule.equals(PawnRule.NOT_EXIST)) {
            return !pawnRule.judge(start, end);
        }
        return true;
    }
}
