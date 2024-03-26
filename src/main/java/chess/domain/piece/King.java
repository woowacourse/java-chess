package chess.domain.piece;

import chess.domain.position.FileDifference;
import chess.domain.position.Position;
import chess.domain.position.RankDifference;

public class King extends Piece {

    public King(Color color) {
        super(color, PieceType.KING);
    }

    @Override
    public boolean canMove(Position from, Position to) {
        return isAllDirectionOneStep(from, to);
    }

    private boolean isAllDirectionOneStep(Position from, Position to) {
        FileDifference fileDifference = from.calculateFileDifferenceTo(to);
        RankDifference rankDifference = from.calculateRankDifferenceTo(to);
        return (fileDifference.hasDistance(1) || fileDifference.hasDistance(0))
                && (rankDifference.hasDistance(1) || rankDifference.hasDistance(0));
    }

    @Override
    public boolean canCatch(Position from, Position to) {
        return canMove(from, to);
    }
}
