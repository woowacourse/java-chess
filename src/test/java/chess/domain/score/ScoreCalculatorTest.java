package chess.domain.score;

import static chess.domain.board.BoardFixtures.createSameColumnPawnBoard;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;

class ScoreCalculatorTest {

	@Test
	void calculate() {
		ScoreCalculator scoreCalculator = new ScoreCalculator();
		Board board = new Board(BoardFactory.initiate());

		assertAll(
				() -> assertThat(scoreCalculator.calculate(board, Team.BLACK)).isEqualTo(38),
				() -> assertThat(scoreCalculator.calculate(board, Team.WHITE)).isEqualTo(38)
		);
	}

	@Test
	void calculateScoreWithSameColumnPawn() {
		ScoreCalculator scoreCalculator = new ScoreCalculator();
		Board board = new Board(createSameColumnPawnBoard());

		assertThat(scoreCalculator.calculate(board, Team.WHITE)).isEqualTo(1.5);
	}
}
