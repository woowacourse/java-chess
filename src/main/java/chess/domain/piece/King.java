package chess.domain.piece;

import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;

public class King extends Piece {

    public King(Color color) {
        super(color, decideRule());
    }

    private static Rule decideRule() {
        return (fileDifference, rankDifference) -> (fileDifference.hasDistance(1) || fileDifference.hasDistance(0))
                && (rankDifference.hasDistance(1) || rankDifference.hasDistance(0));
    }

    @Override
    public boolean isCatchable(FileDifference fileDifference, RankDifference rankDifference) {
        return isMovable(fileDifference, rankDifference);
    }
}
