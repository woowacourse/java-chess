package chess.domain.piece;

import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color, decideRule());
    }

    private static Rule decideRule() {
        return ((fileDifference, rankDifference) -> (fileDifference.hasDistance(1) && rankDifference.hasDistance(2))
                || (fileDifference.hasDistance(2) && rankDifference.hasDistance(1)));
    }

    @Override
    public boolean isCatchable(FileDifference fileDifference, RankDifference rankDifference) {
        return isMovable(fileDifference, rankDifference);
    }
}
