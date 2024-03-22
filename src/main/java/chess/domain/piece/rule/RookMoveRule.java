package chess.domain.piece.rule;

import chess.domain.piece.Rule;
import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;

public class RookMoveRule implements Rule {

    private static final RookMoveRule instance = new RookMoveRule();

    private RookMoveRule() {}

    public static RookMoveRule instance() {
        return instance;
    }

    @Override
    public boolean obey(FileDifference fileDifference, RankDifference rankDifference) {
        return (!fileDifference.hasDistance(0) && rankDifference.hasDistance(0)) ||
                (fileDifference.hasDistance(0) && !rankDifference.hasDistance((0)));
    }
}
