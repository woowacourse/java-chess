package chess.domain.game;

import chess.domain.piece.Color;
import java.util.HashMap;
import java.util.Map;

public class Result {

    private final Map<Color, Double> result = new HashMap<>();

    public Result(double blackScore, double whiteScore) {
        result.put(Color.BLACK, blackScore);
        result.put(Color.WHITE, whiteScore);
    }

    public Map<Color, Double> getResult() {
        return result;
    }

    public Map<Color, String> getWinOrLose() {
        Map<Color, String> winOrLose = new HashMap<>();
        if (result.get(Color.BLACK).equals(result.get(Color.WHITE))) {
            winOrLose.put(Color.BLACK, "무");
            winOrLose.put(Color.WHITE, "무");
            return winOrLose;
        }
        if (result.get(Color.BLACK) > result.get(Color.WHITE)) {
            winOrLose.put(Color.BLACK, "승");
            winOrLose.put(Color.WHITE, "패");
            return winOrLose;
        }
        winOrLose.put(Color.BLACK, "승");
        winOrLose.put(Color.WHITE, "패");
        return winOrLose;
    }
}
