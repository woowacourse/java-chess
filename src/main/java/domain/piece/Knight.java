package domain.piece;

import domain.board.Position;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        int fileDifference = source.calculateFileDifference(target);
        int rankDifference = source.calculateRankDifference(target);

        return (Math.abs(fileDifference) == 1 && Math.abs(rankDifference) == 2)
                || (Math.abs(fileDifference) == 2 && Math.abs(rankDifference) == 1);
    }
}
