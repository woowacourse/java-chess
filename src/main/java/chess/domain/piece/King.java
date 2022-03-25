package chess.domain.piece;

import chess.domain.Position;

public class King extends Piece{

    private static final int SCOPE_DIFFERENCE = 1;

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return isSameRowOrColAndOneDifference(fromPosition, toPosition)
            || isOneDifferenceDiagonal(fromPosition, toPosition);
    }

    private boolean isSameRowOrColAndOneDifference(Position fromPosition, Position toPosition) {
        return isSameColAndOneDifference(fromPosition,toPosition)
            || isSameRowAndOneDifference(fromPosition,toPosition);
    }

    private boolean isSameColAndOneDifference(Position fromPosition, Position toPosition) {
        return fromPosition.isSameAbscissa(toPosition)
            &&  Math.abs(fromPosition.getOrdinateDifference(toPosition)) == SCOPE_DIFFERENCE;
    }

    private boolean isSameRowAndOneDifference(Position fromPosition, Position toPosition) {
        return fromPosition.isSameOrdinate(toPosition)
            &&  Math.abs(fromPosition.getAbscissaDifference(toPosition)) == SCOPE_DIFFERENCE;
    }

    private boolean isOneDifferenceDiagonal(Position fromPosition, Position toPosition) {
        int height = fromPosition.getOrdinateDifference(toPosition);
        int width = fromPosition.getAbscissaDifference(toPosition);
        return Math.pow(height,2) + Math.pow(width,2) == 2;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
