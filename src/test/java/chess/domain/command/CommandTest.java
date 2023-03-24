package chess.domain.command;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Position;

class CommandTest {

	@Test
	@DisplayName("ofStart 메서드 정상 동작 테스트")
	void ofStart_validInput() {
		// given
		final String input = "start";

		// then
		assertDoesNotThrow(() -> Command.ofStart(input));
	}

	@Test
	@DisplayName("명령어가 start가 아니면 예외가 발생한다")
	void ofStart_invalidInput() {
		// given
		final String input = "end";

		// then
		assertThatThrownBy(() -> Command.ofStart(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("게임을 시작하려면 start만 입력해야합니다");
	}

	@Test
	@DisplayName("ofMoveOrEnd 메서드 move 동작 테스트")
	void ofMoveOrEnd_move() {
		// given
		final String input = "move a2 a3";

		// when
		final Command command = Command.ofMoveOrEnd(input);

		// then
		assertAll(
			() -> assertEquals(Position.from("a2"), command.getSource()),
			() -> assertEquals(Position.from("a3"), command.getTarget())
		);
	}

	@Test
	@DisplayName("ofMoveOrEnd 메서드 end 동작 테스트")
	void ofMoveOrEnd_end() {
		// given
		final String input = "end";

		// then
		assertDoesNotThrow(() -> Command.ofMoveOrEnd(input));
	}

	@Test
	@DisplayName("명령어가 end 또는 move가 아니면 예외가 발생한다")
	void ofMoveOrEnd_invalidInput() {
		// given
		final String input = "start";

		// when
		assertThatThrownBy(() -> Command.ofMoveOrEnd(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("게임 진행중에는 end와 move 커맨드 입력만 가능합니다");
	}

	@Test
	@DisplayName("명령어가 end이면 true를 반한한다")
	void isEnd() {
		// given
		final String input = "end";
		final Command command = Command.ofMoveOrEnd(input);

		// then
		assertTrue(command.isEnd());
	}

	@Test
	@DisplayName("명령어가 move이면 true를 반환한다")
	void isMove() {
		// given
		final String input = "move a2 a3";
		final Command command = Command.ofMoveOrEnd(input);

		// then
		assertTrue(command.isMove());
	}
}
