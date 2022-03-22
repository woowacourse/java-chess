import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;

public class BoardTest {

	@DisplayName("보드를 생성한다.")
	@Test
	void create() {
		assertThat(new Board()).isNotNull();
	}
}
