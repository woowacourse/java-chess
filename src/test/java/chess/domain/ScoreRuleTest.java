package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Color;

class ScoreRuleTest {
	@Test
	void calculateScoreTest() {
		Board board = BoardFactory.createNewGame();
		ScoreRule scoreRule = new ScoreRule(board.getPieces());
		Map<Color, Double> scores = scoreRule.calculateScore();

		assertThat(scores.get(Color.BLACK)).isEqualTo(38);
		assertThat(scores.get(Color.WHITE)).isEqualTo(38);
	}
}