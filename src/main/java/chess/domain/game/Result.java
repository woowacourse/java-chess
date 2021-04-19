package chess.domain.game;

import chess.domain.piece.Color;
import java.util.HashMap;
import java.util.Map;

public class Result {

    public static final String DRAW = "무";
    public static final String WIN = "승";
    public static final String LOSE = "패";


    private final Map<Color, Double> result = new HashMap<>();

    public void add(Color color, double score) {
        result.put(color, score);
    }

    public double getScore(Color color) {
        return result.get(color);
    }

    public String winOrLose(Color color) {
        if (result.get(color) > result.get(color.getOppositeColor())) {
            return WIN;
        }
        if (result.get(color).equals(result.get(color.getOppositeColor()))) {
            return DRAW;
        }
        return LOSE;
    }

}
