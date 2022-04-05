package chess.controller.dto;

import chess.domain.board.Result;
import chess.domain.piece.Team;
import java.util.List;
import java.util.Map.Entry;

public class ScoreDto {
    private final String message;

    public ScoreDto(String message) {
        this.message = message;
    }

    public static ScoreDto of(Result result) {
        StringBuilder sb = new StringBuilder();

        List<Team> winnerResult = result.getWinnerResult();

        for (Entry<Team, Double> value : result.getValue().entrySet()) {
            sb.append(value.getKey() + " : " + value.getValue() + "점\n");
        }
        return createFinalScore(sb, winnerResult);
    }

    private static ScoreDto createFinalScore(StringBuilder sb, List<Team> winnerResult) {
        if (winnerResult.size() == 2) {
            sb.append("무승부 입니다!");
            return new ScoreDto(sb.toString());
        }
        sb.append("승리 팀은 : " + winnerResult.get(0) + " 입니다.");
        return new ScoreDto(sb.toString());
    }

    public String getMessage() {
        return message;
    }
}
