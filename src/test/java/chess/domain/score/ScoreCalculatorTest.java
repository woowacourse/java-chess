package chess.domain.score;

import static chess.domain.board.BoardFactory.createSameColumnPawnBoard;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.Board;
import chess.domain.board.InitialBoard;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;

class ScoreCalculatorTest {

	@Test
	void calculate() {
		ScoreCalculator scoreCalculator = new ScoreCalculator();
		Board board = new Board(InitialBoard.createBoard());

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
