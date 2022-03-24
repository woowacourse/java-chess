package chess.domain.piece;

import chess.domain.Position;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return isDiagonalLengthFive(fromPosition, toPosition);
    }

    private boolean isDiagonalLengthFive(Position fromPosition, Position toPosition) {
        int height = fromPosition.getOrdinateDifference(toPosition);
        int width = fromPosition.getAbscissaDifference(toPosition);
        return Math.pow(height, 2) + Math.pow(width, 2) == 5;
    }
}
