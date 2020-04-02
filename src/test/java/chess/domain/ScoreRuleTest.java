package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Color;

class ScoreRuleTest {
	@Test
	void calculateScoreTest() {
		Board board = BoardFactory.createNewGame();
		ScoreRule scoreRule = new ScoreRule(board.getPieces());
		GameResult scores = scoreRule.calculateScore();

		assertThat(scores.getScoreBy(Color.BLACK)).isEqualTo(38);
		assertThat(scores.getScoreBy(Color.WHITE)).isEqualTo(38);
	}
}