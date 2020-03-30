package chess.domain.command;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FirstCommandTest {

	@Test
	void ofTest() {
		String input = "start";
		FirstCommand firstCommand = FirstCommand.of(input.split(" "));

		assertThat(firstCommand).isEqualTo(FirstCommand.START);
	}

	@Test
	void isEndTest() {
		String input = "end";
		FirstCommand firstCommand = FirstCommand.of(input.split(" "));

		assertThat(firstCommand.isEnd()).isTrue();
	}
}