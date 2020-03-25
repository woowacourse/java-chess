package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {
	@Test
	@DisplayName("체스판이 정상적으로 생성된 경우")
	void constructor() {
		assertThat(new Board()).isInstanceOf(Board.class);
	}

	@Test
	@DisplayName("체스판 초기화")
	void initialize() {
		Board board = new Board();
		board.initialize();
		assertThat(board.getRanks().size()).isEqualTo(8);
	}
}
