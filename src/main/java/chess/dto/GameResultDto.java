package chess.dto;

import chess.domain.piece.Color;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResultDto {

    private final Color winnerColor;

    private final double whiteScore;
    private final double blackScore;

    public GameResultDto(Color winner, double whiteScore, double blackScore) {
        this.winnerColor = winner;
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public Color winnerColor() {
        return winnerColor;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public Map<String, Object> getGameResultMap() {
        Map<String, Object> gameResult = new LinkedHashMap<>();
        Map<String, Object> gameScore = new LinkedHashMap<>();

        gameScore.put("winner", winnerColor.getName());
        gameScore.put("whiteScore", whiteScore);
        gameScore.put("blackScore", blackScore);

        gameResult.put("result", gameScore);

        return gameResult;
    }

}
