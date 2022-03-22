import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.view.OutputView;

public class BoardTest {

	@DisplayName("보드를 생성한다.")
	@Test
	void create() {
		assertThat(new Board()).isNotNull();
	}

	@Test
	void tesT() {
		Board board = new Board();
		board.initBoard();
		OutputView.printBoard(board.getBoard());
	}
}
