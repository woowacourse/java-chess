package chess.domain.piece.rule;

import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;

public class QueenMoveRule implements Rule {

    private static final QueenMoveRule instance = new QueenMoveRule();

    private QueenMoveRule() {
    }

    public static QueenMoveRule instance() {
        return instance;
    }

    @Override
    public boolean obey(FileDifference fileDifference, RankDifference rankDifference) {
        return RookMoveRule.instance().obey(fileDifference, rankDifference) ||
                BishopMoveRule.instance().obey(fileDifference, rankDifference);
    }
}
