package chess;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {
	private Board board;

	@BeforeEach
	void setup() {
		board = new Board();
	}

	@DisplayName("displayName")
	@Test
	void construct_test() {
		assertThat(board).isInstanceOf(Board.class);
	}


}
