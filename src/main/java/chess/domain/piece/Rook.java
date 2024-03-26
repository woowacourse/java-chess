package chess.domain.piece;

import chess.domain.position.FileDifference;
import chess.domain.position.Position;
import chess.domain.position.RankDifference;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public boolean canMove(Position from, Position to) {
        FileDifference fileDifference = from.calculateFileDifferenceTo(to);
        RankDifference rankDifference = from.calculateRankDifferenceTo(to);
        return isVerticalMove(fileDifference, rankDifference) ||
                isHorizontalMove(fileDifference, rankDifference);
    }

    private boolean isVerticalMove(FileDifference fileDifference, RankDifference rankDifference) {
        return !fileDifference.hasDistance(0) && rankDifference.hasDistance(0);
    }

    private boolean isHorizontalMove(FileDifference fileDifference, RankDifference rankDifference) {
        return (fileDifference.hasDistance(0)) && !rankDifference.hasDistance(0);
    }

    @Override
    public boolean canCatch(Position from, Position to) {
        return canMove(from, to);
    }
}
