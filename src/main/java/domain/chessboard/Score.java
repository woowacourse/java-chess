package domain.chessboard;

import domain.piece.Color;

import java.util.HashMap;
import java.util.Map;

public class Score {

    Map<Color, Double> value;

    private Score(Map<Color, Double> value) {
        this.value = value;
    }

    public static Score of(ColorScore blackScore, ColorScore whiteScore) {
        Map<Color, Double> value = new HashMap<>();
        value.put(blackScore.getColor(), blackScore.getScore());
        value.put(whiteScore.getColor(), whiteScore.getScore());

        return new Score(value);
    }

    public Map<Color, Double> getValue() {
        return value;
    }
}
