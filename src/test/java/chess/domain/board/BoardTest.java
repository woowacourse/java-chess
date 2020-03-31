package chess.domain.board;

import static chess.domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {
	@Test
	void initBoardTest() {
		assertThat(BoardFactory.create()).isInstanceOf(Board.class);
	}

	@Test
	void isEmptyPositionTest() {
		Board board = BoardFactory.create();
		assertThat(board.isNotEmptyPosition(A3)).isFalse();
	}

	@Test
	void isNotEmptyPositionTest() {
		Board board = BoardFactory.create();
		assertThat(board.isNotEmptyPosition(A6)).isFalse();
	}
}
