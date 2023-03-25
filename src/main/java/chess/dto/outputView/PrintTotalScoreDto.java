package chess.dto.outputView;

import chess.domain.piece.Team;

import java.util.Map;
import java.util.Optional;

public final class PrintTotalScoreDto {

    private final Map<Team, Double> score;

    public PrintTotalScoreDto(final Map<Team, Double> score) {
        this.score = score;
    }

    public static PrintTotalScoreDto from(final double whiteScore, final double blackScore) {
        return new PrintTotalScoreDto(Map.of(Team.WHITE, whiteScore, Team.BLACK, blackScore));
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
