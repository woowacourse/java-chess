package chess.domain.piece.policy.score;

import chess.domain.piece.score.Score;

public interface CalculateScoreStrategy {
    Score calculate(HasMultiplePeerAtFile hasMultiplePeerAtFile);
}
