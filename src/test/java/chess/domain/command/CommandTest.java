package chess.domain.command;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.position.Position;

class CommandTest {

	@Test
	void startCommandTest() {
		assertThat(Command.of("start").isStart()).isTrue();
	}

	@Test
	void moveCommandTest() {
		Command command = Command.of("move a2 a3");
		assertThat(command.isMove()).isTrue();
	}

	@Test
	void getTargetPositionTest() {
		Command command = Command.of("move a2 a3");
		assertThat(command.getTargetPosition()).isEqualTo(Position.of("a2"));
	}

	@Test
	void getDestinationTest() {
		Command command = Command.of("move a2 a3");
		assertThat(command.getDestination()).isEqualTo(Position.of("a3"));
	}

	@Test
	void statusCommandTest() {
		assertThat(Command.of("status").isStatus()).isTrue();
	}
}