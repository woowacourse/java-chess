package chess.game;

import chess.piece.detail.Color;
import java.util.Map;

public class Result {

    private final Map<Color, Double> result;
    private final Color winColor;

    public Result(final Map<Color, Double> result, final Color winColor) {
        this.result = result;
        this.winColor = winColor;
    }

    public Map<Color, Double> getResult() {
        return result;
    }

    public Color getWinColor() {
        return winColor;
    }
}
