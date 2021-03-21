package chess.domain.game;

import chess.domain.piece.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
	private Map<Color, Double> resultStatistics;
	@BeforeEach
	void setUp() {
		resultStatistics = new HashMap<>();
	}

	@Test
	void resultWhite() {
		resultStatistics.put(Color.BLACK, 10d);
		resultStatistics.put(Color.WHITE, 15d);
		Result result = new Result(resultStatistics);
		Map<Color, String> winOrLose = result.getWinOrLose();

		assertThat(winOrLose.get(Color.BLACK)).isEqualTo(Result.LOSE);
		assertThat(winOrLose.get(Color.WHITE)).isEqualTo(Result.WIN);
	}

	@Test
	void resultDraw() {
		resultStatistics.put(Color.BLACK, 15d);
		resultStatistics.put(Color.WHITE, 15d);
		Result result = new Result(resultStatistics);
		Map<Color, String> winOrLose = result.getWinOrLose();

		assertThat(winOrLose.get(Color.BLACK)).isEqualTo(Result.DRAW);
		assertThat(winOrLose.get(Color.WHITE)).isEqualTo(Result.DRAW);
	}

	@Test
	void resultBlack() {
		resultStatistics.put(Color.BLACK, 15d);
		resultStatistics.put(Color.WHITE, 10d);
		Result result = new Result(resultStatistics);
		Map<Color, String> winOrLose = result.getWinOrLose();

		assertThat(winOrLose.get(Color.BLACK)).isEqualTo(Result.WIN);
		assertThat(winOrLose.get(Color.WHITE)).isEqualTo(Result.LOSE);
	}
}
