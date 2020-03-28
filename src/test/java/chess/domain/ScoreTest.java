package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Pawn;
import chess.domain.position.Position;

public class ScoreTest {

	@Test
	void ofTest() {
		ChessPiece chessPiece = new Pawn(Position.of(1, 1), Team.WHITE);

		assertThat(Score.of(chessPiece)).isEqualTo(Score.PAWN_SCORE);
	}

	@Test
	void sumTest() {
		List<Score> scores = Arrays.asList(Score.values());

		assertThat(Score.sum(scores)).isEqualTo(21);
	}

	@Test
	void calculateDuplicatePawnScoreTest() {
		assertThat(Score.calculateDuplicatePawnScore(4)).isEqualTo(2.0);
	}
}
