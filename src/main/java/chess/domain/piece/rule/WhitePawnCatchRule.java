package chess.domain.piece.rule;

import chess.domain.piece.Rule;
import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;

public class WhitePawnCatchRule implements Rule {

    private static final WhitePawnCatchRule instance = new WhitePawnCatchRule();

    private WhitePawnCatchRule() {
    }

    public static WhitePawnCatchRule instance() {
        return instance;
    }

    @Override
    public boolean obey(FileDifference fileDifference, RankDifference rankDifference) {
        return rankDifference.equals(new RankDifference(1)) && fileDifference.hasDistance(1);
    }
}
