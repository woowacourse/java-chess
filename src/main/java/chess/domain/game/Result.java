package chess.domain.game;

import chess.domain.piece.Color;

import java.util.HashMap;
import java.util.Map;

public class Result {

	public static final String DRAW = "무";
	public static final String WIN = "승";
	public static final String LOSE = "패";
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
			winOrLose.put(Color.BLACK, DRAW);
			winOrLose.put(Color.WHITE, DRAW);
			return winOrLose;
		}
		if (result.get(Color.BLACK) > result.get(Color.WHITE)) {
			winOrLose.put(Color.BLACK, WIN);
			winOrLose.put(Color.WHITE, LOSE);
			return winOrLose;
		}
		winOrLose.put(Color.BLACK, LOSE);
		winOrLose.put(Color.WHITE, WIN);
		return winOrLose;
	}
}
