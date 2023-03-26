package chess.dto.outputView;

import chess.domain.piece.Team;

import java.util.Map;
import java.util.Optional;

public final class PrintTotalScoreDto {

    private final Map<String, Double> score;

    public PrintTotalScoreDto(final Map<String, Double> score) {
        this.score = score;
    }

    public static PrintTotalScoreDto from(final double whiteScore, final double blackScore) {
        return new PrintTotalScoreDto(Map.of(Team.WHITE.name(), whiteScore, Team.BLACK.name(), blackScore));
    }

    public Optional<String> whosWinner() {
        final Double whiteScore = score.get(Team.WHITE.name());
        final Double blackScore = score.get(Team.BLACK.name());
        if (whiteScore < blackScore) {
            return Optional.of(Team.BLACK.name());
        }
        if (whiteScore > blackScore) {
            return Optional.of(Team.WHITE.name());
        }
        return Optional.empty();
    }

    public Map<String, Double> getScore() {
        return score;
    }
}
