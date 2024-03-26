package chess.domain.piece;

import chess.domain.position.FileDifference;
import chess.domain.position.Position;
import chess.domain.position.RankDifference;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public boolean canCatch(Position from, Position to) {
        return canMove(from, to);
    }

    @Override
    public boolean canMove(Position from, Position to) {
        FileDifference fileDifference = from.calculateFileDifferenceTo(to);
        RankDifference rankDifference = from.calculateRankDifferenceTo(to);
        return isDiagonalMove(fileDifference, rankDifference);
    }

    private boolean isDiagonalMove(FileDifference fileDifference, RankDifference rankDifference) {
        return fileDifference.hasSameDistance(rankDifference);
    }
}
