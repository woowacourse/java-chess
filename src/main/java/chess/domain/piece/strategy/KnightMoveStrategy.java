package chess.domain.piece.strategy;

import chess.domain.square.Square;

import java.util.Arrays;

public class KnightMoveStrategy implements MoveStrategy {

    @Override
    public boolean canMove(final Square current, final Square destination) {
        int fileDifference = destination.getFileDifference(current);
        int rankDifference = destination.getRankDifference(current);
        return KnightDirection.isExist(fileDifference, rankDifference);
    }
}
