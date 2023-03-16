package chess.domain.piece.strategy;

import chess.domain.square.Square;

public class DiagonalMoveStrategy implements MoveStrategy {

    @Override
    public boolean canMove(final Square current, final Square destination) {
        int fileDifference = destination.getFileDifference(current);
        int rankDifference = destination.getRankDifference(current);
        return Math.abs(fileDifference) == Math.abs(rankDifference);
    }
}
