package chess.domain.board;

import chess.domain.piece.Color;
import java.util.Map;

public class StatusResult {

    private final Map<Color, Double> scoreByColor;
    private final Result result;


    public StatusResult(Map<Color, Double> scoreByColor, Result result) {
        this.scoreByColor = scoreByColor;
        this.result = result;
    }

    public double getWhiteScore() {
        return scoreByColor.get(Color.WHITE);
    }

    public double getBlackScore() {
        return scoreByColor.get(Color.BLACK);
    }

    public Result getResult() {
        return result;
    }
}
