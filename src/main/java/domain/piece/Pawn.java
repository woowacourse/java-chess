package domain.piece;

import domain.board.Position;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition) {
        int rankDifference = targetPosition.calculateRankDifference(sourcePosition);
        return (sourcePosition.isPawnInitialPosition() && rankDifference == 2)
            || rankDifference == 1;
    }
}
