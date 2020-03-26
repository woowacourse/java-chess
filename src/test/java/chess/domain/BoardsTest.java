package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardsTest {
	@Test
	void create_By_Factory() {
		assertThat(BoardFactory.create()).isInstanceOf(Boards.class);
	}
}