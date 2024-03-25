package chess.domain.piece.rule;

import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;

public class KingMoveRule implements Rule {

    private static final KingMoveRule instance = new KingMoveRule();

    private KingMoveRule() {
    }

    public static KingMoveRule instance() {
        return instance;
    }

    @Override
    public boolean obey(FileDifference fileDifference, RankDifference rankDifference) {
        return (fileDifference.hasDistance(1) || fileDifference.hasDistance(0))
                && (rankDifference.hasDistance(1) || rankDifference.hasDistance(0))
                && !(fileDifference.hasDistance(0) && rankDifference.hasDistance(0));
    }
}
