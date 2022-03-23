package chess.view;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class MenuTest {
	@DisplayName("생성 확인")
	@Test
	void create() {
		// given
		String input = "start";

		// then
		assertDoesNotThrow(() -> Menu.of(input));
	}
}