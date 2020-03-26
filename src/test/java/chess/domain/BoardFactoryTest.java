package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardFactoryTest {
	@Test
	void create() {
		assertThat(BoardFactory.create()).isInstanceOf(Boards.class);
	}
}