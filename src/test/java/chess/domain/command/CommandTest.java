package chess.domain.command;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
	@DisplayName("ofCommand 메서드 end 동작 테스트")
	void ofMoveOrEnd_end() {
		// given
		final String input = "end";

		// then
		assertDoesNotThrow(() -> Command.ofCommand(input));
	}

	@Test
	@DisplayName("명령어가 end, move 또는 status가 아니면 예외가 발생한다")
	void ofMoveOrEnd_invalidInput() {
		// given
		final String input = "start";

		// when
		assertThatThrownBy(() -> Command.ofCommand(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("게임 진행중에는 end와 move, status 커맨드 입력만 가능합니다");
	}

	@Test
	@DisplayName("명령어가 end이면 true를 반한한다")
	void isEnd() {
		// given
		final String input = "end";
		final Command command = Command.ofCommand(input);

		// then
		assertTrue(command.isEnd());
	}

	@Test
	@DisplayName("명령어가 move이면 true를 반환한다")
	void isMove() {
		// given
		final String input = "move a2 a3";
		final Command command = Command.ofCommand(input);

		// then
		assertTrue(command.isMove());
	}

	@Test
	@DisplayName("명령어가 status이면 true를 반환한다")
	void isStatus() {
		// given
		final String input = "status";
		final Command command = Command.ofCommand(input);

		// then
		assertTrue(command.isStatus());
	}

	@Test
	@DisplayName("게임 진행 중 명령어가 move, end, status가 아니면 예외가 발생한다")
	void onlyMoveEndStatusCommand() {
		// given
		final String input = "stop";

		assertThatThrownBy(() -> Command.ofCommand(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("입력값은 start, end, move, status만 가능합니다");
	}

	@Test
	@DisplayName("명령어가 move일 경우 'move 출발지와 도착지' 형식이 아니면 예외가 발생한다")
	void sourceAndTargetEach() {
		// given
		final String input = "move a2 a3 a4";

		assertThatThrownBy(() -> Command.ofCommand(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("게임 이동은 move source target 형식으로 입력해야 합니다");
	}

	@Test
	@DisplayName("출발지와 도착지가 두글자씩이 아니면 예외가 발생한다")
	void twoLettersEach() {
		// given
		final String input = "move a123 b";

		assertThatThrownBy(() -> Command.ofCommand(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("source target은 두글자씩 입력해주세요(예: b2 b3)");
	}
}
