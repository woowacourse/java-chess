package chess.position;

import chess.Rule;

public class PositionDifference {

    private final FileDifference fileDifference;
    private final RankDifference rankDifference;

    public PositionDifference(FileDifference fileDifference, RankDifference rankDifference) {
        this.fileDifference = fileDifference;
        this.rankDifference = rankDifference;
    }

    public boolean isObeyRule(Rule rule) {
        return rule.obey(fileDifference, rankDifference);
    }
}
