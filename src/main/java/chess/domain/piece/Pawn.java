package chess.domain.piece;

import chess.domain.Ordinate;
import chess.domain.Position;

public class Pawn extends Piece {

    private static final int ABSCISSA_DIFFERENCE = 1;

    public Pawn(Color color) {
        super(color);
    }

    public boolean isInitialPosition(Position fromPosition) {
        return fromPosition.isSameOrdinate(Ordinate.TWO)
            || fromPosition.isSameOrdinate(Ordinate.SEVEN);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        int difference = ABSCISSA_DIFFERENCE;
        if (color == Color.WHITE) {
            difference = -ABSCISSA_DIFFERENCE;
        }
        if (isInitialPosition(fromPosition)) {
            return canMovePosition(fromPosition, toPosition, difference)
                || canMovePosition(fromPosition, toPosition, difference * 2);
        }
        return canMovePosition(fromPosition, toPosition, difference);
    }

    private boolean canMovePosition(Position fromPosition, Position toPosition, int difference) {
        return fromPosition.isSameAbscissa(toPosition)
            && fromPosition.getOrdinateDifference(toPosition) == difference;
    }
}
