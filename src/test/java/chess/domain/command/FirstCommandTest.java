package chess.domain.command;

import static chess.domain.command.Command.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class FirstCommandTest {
	@DisplayName("입력값에 null 또는 빈 값이 들어왔을 때 에외처리")
	@ParameterizedTest
	@NullAndEmptySource
	void validateNullAndEmptyTest(String input) {
		assertThatThrownBy(() -> {
			new FirstCommand(input);
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("입력값에 길이가 1이 아닌 값이 들어왔을 때 에외처리")
	@ParameterizedTest
	@ValueSource(strings = {"hello world", "hi hi hi"})
	void validateLengthTest(String input) {
		assertThatThrownBy(() -> {
			new FirstCommand(input);
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("입력값에 start, end가 아닌 값이 들어왔을 때 에외처리")
	@ParameterizedTest
	@ValueSource(strings = {"status", "move"})
	void validateStartOrEndTest(String input) {
		assertThatThrownBy(() -> {
			new FirstCommand(input);
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("입력이 end인지 확인하는 메서드 테스")
	@Test
	void isEndTest() {
		String input = "end";
		FirstCommand firstCommand = new FirstCommand(input);

		assertThat(firstCommand.isEnd()).isTrue();
	}
}