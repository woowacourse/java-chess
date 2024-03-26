package chess.domain.piece;

import chess.domain.position.FileDifference;
import chess.domain.position.Position;
import chess.domain.position.RankDifference;

public class Pawn extends Piece {

    private boolean isMoved = false;

    public Pawn(Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public boolean canMove(Position from, Position to) {
        if (!isMoved) {
            isMoved = true;
            return canFirstMove(from, to);
        }
        return canNormalMove(from, to);
    }

    @Override
    public boolean canCatch(Position from, Position to) {
        FileDifference fileDifference = from.calculateFileDifferenceTo(to);
        RankDifference rankDifference = from.calculateRankDifferenceTo(to);
        if (isSameColor(Color.WHITE)) {
            return rankDifference.hasDistance(1) &&
                    fileDifference.hasDistance(1);
        }
        return rankDifference.hasSameDifference(-1) &&
                fileDifference.hasDistance(1);
    }

    private boolean canFirstMove(Position from, Position to) {
        RankDifference rankDifference = from.calculateRankDifferenceTo(to);
        if (isSameColor(Color.BLACK)) {
            return rankDifference.hasSameDifference(-2) || canNormalMove(from, to);
        }
        return rankDifference.hasSameDifference(2) || canNormalMove(from, to);
    }

    private boolean canNormalMove(Position from, Position to) {
        FileDifference fileDifference = from.calculateFileDifferenceTo(to);
        RankDifference rankDifference = from.calculateRankDifferenceTo(to);
        if (isSameColor(Color.WHITE)) {
            return rankDifference.hasSameDifference(1)
                    && fileDifference.hasDistance(0);
        }
        return rankDifference.hasSameDifference(-1)
                && fileDifference.hasDistance(0);
    }
}
