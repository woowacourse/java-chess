package domain.piece;

import domain.board.Position;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        int rankDifference = target.calculateRankDifference(source);
        int fileDifference = target.calculateFileDifference(source);

        if (isWhite()) {
            return (source.isWhitePawnInitialPosition() && rankDifference == 2)
                    || (rankDifference == 1 && Math.abs(fileDifference) <= 1);
        }
        return (source.isBlackPawnInitialPosition() && rankDifference == -2)
                || (rankDifference == -1 && Math.abs(fileDifference) <= 1);
    }

    @Override
    public String asString() {
        return "P";
    }
}
