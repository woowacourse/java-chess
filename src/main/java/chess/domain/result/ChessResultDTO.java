package chess.domain.result;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ChessResultDTO {
    private final Map<String, Double> scores;

    public ChessResultDTO(ChessResult chessResult) {
        this.scores = chessResult.getResult().entrySet()
                .stream()
                .collect(toMap(entry -> entry.getKey().getName(), entry -> entry.getValue().getScore()));
    }

    public Map<String, Double> getScores() {
        return scores;
    }
}
