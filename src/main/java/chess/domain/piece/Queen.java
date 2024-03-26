package chess.domain.piece;

import chess.domain.position.FileDifference;
import chess.domain.position.Position;
import chess.domain.position.RankDifference;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public boolean canMove(Position from, Position to) {
        FileDifference fileDifference = from.calculateFileDifferenceTo(to);
        RankDifference rankDifference = from.calculateRankDifferenceTo(to);
        return isVerticalMove(fileDifference, rankDifference) ||
                isHorizontalMove(fileDifference, rankDifference)
                || isDiagonalMove(fileDifference, rankDifference);
    }

    private boolean isVerticalMove(FileDifference fileDifference, RankDifference rankDifference) {
        return !fileDifference.hasDistance(0) && rankDifference.hasDistance(0);
    }

    private boolean isHorizontalMove(FileDifference fileDifference, RankDifference rankDifference) {
        return (fileDifference.hasDistance(0)) && !rankDifference.hasDistance(0);
    }

    private boolean isDiagonalMove(FileDifference fileDifference, RankDifference rankDifference) {
        return fileDifference.hasSameDistance(rankDifference);
    }

    @Override
    public boolean canCatch(Position from, Position to) {
        return canMove(from, to);
    }
}
