package chess.domain.piece;

import chess.domain.Ordinate;
import chess.domain.Position;

public class Pawn extends Piece {

    private static final int ABSCISSA_DIFFERENCE = 1;
    private static final double PAWN_SCORE = 1;

    public Pawn(Color color) {
        super(color, PAWN_SCORE);
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

    @Override
    public boolean isCatchable(Position fromPosition, Position toPosition) {
        if (color == Color.WHITE) {
            return isOneDifferenceDiagonal(fromPosition, toPosition)
                && toPosition.getOrdinateDifference(fromPosition) > 0;
        }
        return isOneDifferenceDiagonal(fromPosition, toPosition)
            && toPosition.getOrdinateDifference(fromPosition) < 0;
    }

    private boolean isOneDifferenceDiagonal(Position fromPosition, Position toPosition) {
        int height = fromPosition.getOrdinateDifference(toPosition);
        int width = fromPosition.getAbscissaDifference(toPosition);
        return Math.pow(height,2) + Math.pow(width,2) == 2;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
