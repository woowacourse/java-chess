package chess.domain.piece.rule;

import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;

public class BlackPawnMoveRule implements Rule {

    private static final BlackPawnMoveRule instance = new BlackPawnMoveRule();

    private BlackPawnMoveRule() {
    }

    public static BlackPawnMoveRule instance() {
        return instance;
    }

    @Override
    public boolean obey(FileDifference fileDifference, RankDifference rankDifference) {
        return rankDifference.equals(new RankDifference(-1)) && fileDifference.hasDistance(0);
    }
}
