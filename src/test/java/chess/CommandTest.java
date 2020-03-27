package chess;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

	@DisplayName("유효하지 않는 명령어 입력시 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"hello", "star!"})
	void ofTest(String input) {
		assertThatThrownBy(() -> InGameCommand.of(input)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("존재하지 않는 명령어입니다.");
	}
}