package chess.domain.piece.rule;

import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;

public class WhitePawnMoveRule implements Rule {

    private static final WhitePawnMoveRule instance = new WhitePawnMoveRule();

    private WhitePawnMoveRule() {
    }

    public static WhitePawnMoveRule instance() {
        return instance;
    }

    @Override
    public boolean obey(FileDifference fileDifference, RankDifference rankDifference) {
        return rankDifference.equals(new RankDifference(1));
    }
}
