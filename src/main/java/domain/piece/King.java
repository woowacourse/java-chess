package domain.piece;

import domain.board.Position;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        int rankDifference = source.calculateRankDifference(target);
        int fileDifference = source.calculateFileDifference(target);
        return Math.abs(rankDifference) <= 1 && Math.abs(fileDifference) <= 1;
    }
}
