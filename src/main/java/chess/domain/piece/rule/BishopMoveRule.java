package chess.domain.piece.rule;

import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;

public class BishopMoveRule implements Rule {

    private static final BishopMoveRule instance = new BishopMoveRule();

    private BishopMoveRule() {
    }

    public static BishopMoveRule instance() {
        return instance;
    }

    @Override
    public boolean obey(FileDifference fileDifference, RankDifference rankDifference) {
        return fileDifference.hasSameDistance(rankDifference) &&
                !fileDifference.hasDistance(0);
    }
}
