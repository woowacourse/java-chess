package chess.dto.outputView;

import java.util.Map;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

public final class PrintTotalScoreDto {

    private final Map<String, Double> score;

    public PrintTotalScoreDto(final double whiteScore, final double blackScore) {
        this.score = Map.of(WHITE.name(), whiteScore, BLACK.name(), blackScore);
    }

    public Map<String, Double> getScore() {
        return score;
    }
}
