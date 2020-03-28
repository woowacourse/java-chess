package chess;

import static org.assertj.core.api.Assertions.*;

import java.util.regex.Pattern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

	@DisplayName("유효하지 않는 명령어 입력시 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"move  a3 a4"})
	void ofTest(String input) {
		assertThatThrownBy(() -> Command2.of(input)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("존재하지 않는 명령어입니다.");
	}

	@DisplayName("displayName")
	@Test
	void name() {
		assertThatCode(() -> Command2.of("move a3 a6")).doesNotThrowAnyException();
	}
}