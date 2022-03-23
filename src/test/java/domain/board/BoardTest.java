package domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.Test;

class BoardTest {

	@Test
	void checkStartBoard() {
		Board board = new Board();

		assertAll(
				() -> assertThat(board.isBlank(Position.of(4, 4))).isTrue(),
				() -> assertThat(board.isBlank(Position.of(1, 1))).isFalse(),
				() -> assertThat(board.isBlank(Position.of(8, 8))).isFalse()
		);
	}
}
