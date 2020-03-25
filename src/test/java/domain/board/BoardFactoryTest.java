package domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BoardFactoryTest {
	@Test
	void create_CreateInitialBoard() {
		assertThat(BoardFactory.create()).isInstanceOf(Board.class);
	}
}
