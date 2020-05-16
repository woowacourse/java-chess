package chess.domain.policy.score;

import chess.domain.piece.policy.score.CalculateScoreStrategy;
import chess.domain.piece.policy.score.HasMultiplePeerAtFile;
import chess.domain.piece.score.Score;

public class PawnScoreCalculator implements CalculateScoreStrategy {
    private static final Score SCORE_WHEN_HAS_NOT_PEER = Score.of(1);
    private static final Score SCORE_WHEN_HAS_PEER = Score.of(0.5);

    @Override
    public Score calculate(HasMultiplePeerAtFile hasMultiplePeerAtFile) {
        if (hasMultiplePeerAtFile.has()) {
            return SCORE_WHEN_HAS_PEER;
        }
        return SCORE_WHEN_HAS_NOT_PEER;
    }
}
