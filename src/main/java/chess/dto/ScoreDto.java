package chess.dto;

import chess.domain.piece.Team;

import java.util.Map;
import java.util.Optional;

public final class ScoreDto {

    private final Map<Team, Double> score;

    public ScoreDto(final Map<Team, Double> score) {
        this.score = score;
    }

    public static ScoreDto from(final double whiteScore, final double blackScore) {
        return new ScoreDto(Map.of(chess.domain.piece.Team.WHITE, whiteScore, chess.domain.piece.Team.BLACK, blackScore));
    }

    public Optional<Team> whosWinner() {
        final Double whiteScore = score.get(Team.WHITE);
        final Double blackScore = score.get(Team.BLACK);
        if (whiteScore < blackScore) {
            return Optional.of(Team.BLACK);
        }
        if (whiteScore > blackScore) {
            return Optional.of(Team.WHITE);
        }
        return Optional.empty();
    }

    public Map<Team, Double> getScore() {
        return score;
    }
}
