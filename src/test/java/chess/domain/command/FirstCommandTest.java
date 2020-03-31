package chess.domain.command;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

/**
 *    class description
 *
 *    @author AnHyungJu
 */
class FirstCommandTest {
	@DisplayName("명령어 길이가 1이 아닐때 예외처리")
	@Test
	void ofTest() {
		String[] command = {"move", "a1", "b2"};

		assertThatThrownBy(() -> FirstCommand.of(command))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("길이가 1");
	}

	@DisplayName("명령어가 null이나 빈 값일 때 예외처리")
	@ParameterizedTest
	@NullAndEmptySource
	void ofTest2(String[] command) {
		assertThatThrownBy(() -> FirstCommand.of(command))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("null이나 빈 값");
	}

	@DisplayName("명령어가 start나 end가 아닐 때 예외처리")
	@Test
	void ofTest3() {
		String[] command = {"move"};

		assertThatThrownBy(() -> FirstCommand.of(command))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("start나 end");
	}

	@DisplayName("생성된 명령어가 end면 true 반환")
	@Test
	void isEndTest() {
		String[] command = {"end"};

		assertThat(FirstCommand.of(command).isEnd()).isTrue();
	}
}