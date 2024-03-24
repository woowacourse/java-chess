package chess.domain.piece;

import chess.domain.position.Difference;
import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color, Difference::hasSameDistance);
    }

    @Override
    public boolean isCatchable(FileDifference fileDifference, RankDifference rankDifference) {
        return isMovable(fileDifference, rankDifference);
    }
}
