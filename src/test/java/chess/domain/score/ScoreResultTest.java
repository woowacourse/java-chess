package chess.domain.score;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.Team;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ScoreResultTest {

	@ParameterizedTest
	@CsvSource(value = {"10, 9, BLACK", "9, 10, WHITE", "10, 10, NEUTRALITY"})
	void getWinner(int blackScore, int whiteScore, Team winner) {
		ScoreResult scoreResult = new ScoreResult(blackScore, whiteScore);

		assertThat(scoreResult.getWinner()).isEqualTo(winner);
	}
}
