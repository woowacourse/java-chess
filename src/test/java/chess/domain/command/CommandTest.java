package chess.domain.command;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Position;

class CommandTest {

	@Test
	@DisplayName("start 커맨드가 start 상태인지 확인하는 테스트")
	void startCommandTest() {
		assertThat(Command.of("start").isStart()).isTrue();
	}

	@Test
	@DisplayName("move 커맨드가 move 상태인지 확인하는 테스트")
	void moveCommandTest() {
		Command command = Command.of("move a2 a3");
		assertThat(command.isMove()).isTrue();
	}

	@Test
	@DisplayName("move 커맨드에서 뒤 인자들 잘 받아왔는지 확인하는 테스트")
	void getTargetPositionTest() {
		Command command = Command.of("move a2 a3");
		assertThat(command.getTargetPosition()).isEqualTo(Position.of("a2"));
	}

	@Test
	@DisplayName("move 커맨드에서 뒤 인자들 잘 받아왔는지 확인하는 테스트")
	void getDestinationTest() {
		Command command = Command.of("move a2 a3");
		assertThat(command.getDestination()).isEqualTo(Position.of("a3"));
	}

	@Test
	@DisplayName("status 커맨드가 status 상태인지 확인하는 테스트")
	void statusCommandTest() {
		assertThat(Command.of("status").isStatus()).isTrue();
	}
}