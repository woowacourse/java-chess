package chess.domain.piece.rule;

import chess.domain.piece.Rule;
import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;

public class BlackPawnCatchRule implements Rule {

    private static final BlackPawnCatchRule instance = new BlackPawnCatchRule();

    private BlackPawnCatchRule() {
    }

    public static BlackPawnCatchRule instance() {
        return instance;
    }

    @Override
    public boolean obey(FileDifference fileDifference, RankDifference rankDifference) {
        return rankDifference.equals(new RankDifference(-1)) && fileDifference.hasDistance(1);
    }
}
