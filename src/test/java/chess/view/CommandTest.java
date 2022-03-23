package chess.view;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class CommandTest {
	@DisplayName("생성 확인")
	@Test
	void create() {
		String input = "start";

		assertDoesNotThrow(() -> Command.of(input));
		// then
	}
}