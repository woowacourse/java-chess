package domain.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RowOfBoardTest {

	@Test
	void createEmpty() {
		assertThat(RowOfBoard.createEmpty()).isNotNull();
	}
}