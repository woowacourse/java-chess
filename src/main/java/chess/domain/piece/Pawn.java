package chess.domain.piece;

import chess.domain.Position;

public class Pawn extends Piece {

    private static final int ABSCISSA_DIFFERENCE = 1;

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        int difference = ABSCISSA_DIFFERENCE;
        if (color == Color.WHITE) {
            difference = -ABSCISSA_DIFFERENCE;
        }
        return fromPosition.isSameAbscissa(toPosition)
            && fromPosition.getOrdinateDifference(toPosition) == difference;
    }


}
