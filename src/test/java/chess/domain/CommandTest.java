package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

/**
 *    class description
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class CommandTest {
	@DisplayName("String[]input이 null이나 빈 값이 들어왔을 때 예외처리")
	@ParameterizedTest
	@NullAndEmptySource
	void ofTest(String[] input) {
		assertThatThrownBy(() -> Command.of(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("null이나 빈 값");
	}

	@DisplayName("String[]의 각 인덱스 값이 null이나 빈 값이 들어왔을 때 예외처리")
	@Test
	void ofTest2() {
		assertThatThrownBy(() -> Command.of(new String[] {"a", "", " "}))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("null이나 빈 값");
	}

	@DisplayName("유효하지 않은 값이 들어왔을 때 예외처리")
	@Test
	void ofTest3() {
		assertThatThrownBy(() -> Command.of(new String[] {"a", "b", "c"}))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("유효하지");
	}

	@DisplayName("명령어가 end일 때 true를 반환")
	@Test
	void isEndTest() {
		Command command = Command.of(new String[] {"end"});

		assertThat(command.isEnd()).isTrue();
	}

	@DisplayName("명령어가 move일 때 true를 반환")
	@Test
	void isMoveTest() {
		Command command = Command.of(new String[] {"move", "a1", "b1"});

		assertThat(command.isMove()).isTrue();
	}

	@DisplayName("명령어가 status 때 true를 반환")
	@Test
	void isStatusTest() {
		Command command = Command.of(new String[] {"status"});

		assertThat(command.isStatus()).isTrue();
	}
}
