package chess.controller.dto;

import chess.domain.ChessGameService;
import chess.domain.board.Result;
import chess.domain.piece.Team;
import java.util.List;
import java.util.Map.Entry;

public class ScoreDto {

    private static final String DRAW_MESSAGE = "무승부 입니다!";
    private static final String COLON = " : ";
    private static final String SCORE_GUIDE_MESSAGE = "점\n";
    private static final int DRAW_STANDARD = 2;

    private final String message;

    public ScoreDto(String message) {
        this.message = message;
    }

    public static ScoreDto of(Result result) {
        StringBuilder sb = new StringBuilder();
        List<Team> winnerResult = result.getWinnerResult();

        for (Entry<Team, Double> value : result.getValue().entrySet()) {
            if (value.getKey().isNone()) {
                continue;
            }
            sb.append(value.getKey() + COLON + value.getValue() + SCORE_GUIDE_MESSAGE);
        }
        return createFinalScore(sb, winnerResult);
    }

    private static ScoreDto createFinalScore(StringBuilder sb, List<Team> winnerResult) {
        if (winnerResult.size() == DRAW_STANDARD) {
            sb.append(DRAW_MESSAGE);
            return new ScoreDto(sb.toString());
        }
        sb.append(String.format(ChessGameService.WIN_MESSAGE, winnerResult.get(0)));
        return new ScoreDto(sb.toString());
    }

    public String getMessage() {
        return message;
    }
}
