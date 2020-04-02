package chess.domain.command;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import chess.domain.coordinates.Coordinates;

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
		assertThat(command.getTargetCoordinates()).isEqualTo(Coordinates.of("a2"));
	}

	@Test
	void getDestinationTest() {
		Command command = Command.of("move a2 a3");
		assertThat(command.getDestination()).isEqualTo(Coordinates.of("a3"));
	}

	@Test
	void statusCommandTest() {
		assertThat(Command.of("status").isStatus()).isTrue();
	}

	@Test
	void endCommandTest() {
		assertThat(Command.of("end").isEnd()).isTrue();

	}
}