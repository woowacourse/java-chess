package chess.domain.policy.score;

import chess.domain.piece.policy.score.CalculateScoreStrategy;
import chess.domain.piece.policy.score.HasMultiplePeerAtFile;
import chess.domain.piece.score.Score;

public class DefaultScoreCalculator implements CalculateScoreStrategy {
    private final Score score;

    public DefaultScoreCalculator(Score score) {
        this.score = score;
    }

    @Override
    public Score calculate(HasMultiplePeerAtFile hasMultiplePeerAtFile) {
        return score;
    }
}
