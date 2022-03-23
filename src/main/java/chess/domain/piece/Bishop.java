package chess.domain.piece;

import chess.domain.Position;

public class Bishop extends Piece{

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        int height = fromPosition.getOrdinateDifference(toPosition);
        int width = fromPosition.getAbscissaDifference(toPosition);
        return Math.pow(height,2) == Math.pow(width,2);
    }
}
