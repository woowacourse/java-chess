package domain.chessboard;

import domain.piece.Color;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {

    Map<Color, GameResult> value;

    private Result(Map<Color, GameResult> value) {
        this.value = value;
    }

    public static Result createByCheckMate(boolean blackHasKing, boolean whiteHasKing) {
        Map<Color, GameResult> value = new LinkedHashMap<>();
        value.put(Color.BLACK, getGameResultByKing(blackHasKing));
        value.put(Color.WHITE, getGameResultByKing(whiteHasKing));

        return new Result(value);
    }

    private static GameResult getGameResultByKing(boolean hasKing) {
        if (hasKing) {
            return GameResult.WIN;
        }

        return GameResult.LOSE;
    }

    public static Result createByScore(Score score) {
        Map<Color, GameResult> value = new LinkedHashMap<>();
        Map<Color, Double> scoreValue = score.getValue();

        double blackScore = scoreValue.get(Color.BLACK);
        double whiteScore = scoreValue.get(Color.WHITE);

        winBlack(value, blackScore, whiteScore);
        winWhite(value, blackScore, whiteScore);
        draw(value, blackScore, whiteScore);
        return new Result(value);
    }

    private static void draw(Map<Color, GameResult> value, double blackScore, double whiteScore) {
        if (blackScore == whiteScore) {
            value.put(Color.BLACK, GameResult.DRAW);
            value.put(Color.WHITE, GameResult.DRAW);
        }
    }

    private static void winWhite(Map<Color, GameResult> value, double blackScore, double whiteScore) {
        if (blackScore < whiteScore) {
            value.put(Color.BLACK, GameResult.LOSE);
            value.put(Color.WHITE, GameResult.WIN);
        }
    }

    private static void winBlack(Map<Color, GameResult> value, double blackScore, double whiteScore) {
        if (blackScore > whiteScore) {
            value.put(Color.BLACK, GameResult.WIN);
            value.put(Color.WHITE, GameResult.LOSE);
        }
    }

    public Map<Color, GameResult> getValue() {
        return value;
    }

}
