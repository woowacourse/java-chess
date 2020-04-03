package chess.domain.result;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ChessResultDTO {

    private final Map<String, Double> scores;

    private ChessResultDTO(Map<String, Double> scores) {
        this.scores = scores;
    }

    public static ChessResultDTO create(ChessResult chessResult) {
        Map<String, Double> scores = chessResult.getResult().entrySet()
                .stream()
                .collect(toMap(entry -> entry.getKey().getName(), entry -> entry.getValue().getScore()));

        return new ChessResultDTO(scores);
    }

    public Map<String, Double> getScores() {
        return scores;
    }
}
