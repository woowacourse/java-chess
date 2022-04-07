package chess.web.dto;

import chess.domain.Color;
import chess.domain.Result;
import java.util.Map;

public class ResultDto {

    private int whiteScore;
    private int blackScore;
    private String winner;

    public ResultDto(int whiteScore, int blackScore, Map<Result, Color> result) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
        if (result.containsKey(Result.WIN)) {
            this.winner = result.get(Result.WIN).name().toLowerCase();
            return;
        }
        this.winner = "none";
    }

    public int getWhiteScore() {
        return whiteScore;
    }

    public int getBlackScore() {
        return blackScore;
    }

    public String getWinner() {
        return winner;
    }
}
