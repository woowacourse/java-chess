package chess;

import static org.assertj.core.api.Assertions.*;

import chess.exception.CommandException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {
	@DisplayName("유효하지 않는 명령어 입력시 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"hello", "star!"})
	void ofTest(String input) {
		assertThatThrownBy(() -> Command.beforeGameCommandOf(input)).isInstanceOf(CommandException.class)
				.hasMessage("잘못된 명령어를 입력하셨습니다.");
		assertThatThrownBy(() -> Command.inGameCommandOf(input)).isInstanceOf(CommandException.class)
				.hasMessage("잘못된 명령어를 입력하셨습니다.");
	}
}