package view.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RowOfBoardTest {

	@Test
	void createEmpty() {
		assertThat(RowOfBoard.createEmpty()).isNotNull();
	}
}