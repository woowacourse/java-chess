package chess.domain.piece;

import chess.domain.position.FileDifference;
import chess.domain.position.Position;
import chess.domain.position.RankDifference;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        return isMovable(from, to);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        FileDifference fileDifference = from.calculateFileDifferenceTo(to);
        RankDifference rankDifference = from.calculateRankDifferenceTo(to);
        return isDiagonalMove(fileDifference, rankDifference);
    }

    private boolean isDiagonalMove(FileDifference fileDifference, RankDifference rankDifference) {
        return fileDifference.hasSameDistance(rankDifference);
    }
}
