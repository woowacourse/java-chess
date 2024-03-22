package chess.domain.piece.rule;

import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;

public class KnightMoveRule implements Rule {

    private static final KnightMoveRule instance = new KnightMoveRule();

    private KnightMoveRule() {
    }

    public static KnightMoveRule instance() {
        return instance;
    }

    @Override
    public boolean obey(FileDifference fileDifference, RankDifference rankDifference) {
        return (fileDifference.hasDistance(1) && rankDifference.hasDistance(2))
                || (fileDifference.hasDistance(2) && rankDifference.hasDistance(1));
    }
}
