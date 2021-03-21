package chess.domain.game;

import chess.domain.piece.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {
	private static final int LOSER_INDEX = 0;
	private static final int WINNER_INDEX = 1;

	private final Map<Color, Double> result;

	public Result(Map<Color, Double> result) {
		this.result = result;
	}

	public Map<Color, Double> getResult() {
		return result;
	}

	public Map<Color, Outcome> getWinOrLose() {
		Map<Color, Outcome> winOrLose = new HashMap<>();
		if (result.get(Color.BLACK).equals(result.get(Color.WHITE))) {
			winOrLose.put(Color.BLACK, Outcome.DRAW);
			winOrLose.put(Color.WHITE, Outcome.DRAW);
			return winOrLose;
		}

		List<Map.Entry<Color, Double>> results = new ArrayList<>(result.entrySet());
		results.sort(Map.Entry.comparingByValue());
		winOrLose.put(results.get(LOSER_INDEX).getKey(), Outcome.LOSE);
		winOrLose.put(results.get(WINNER_INDEX).getKey(), Outcome.WIN);
		return winOrLose;
	}
}
