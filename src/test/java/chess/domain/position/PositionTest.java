package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {

	@DisplayName("Position 생성자 작동 테스트")
	@Test
	void create_normal_constructor() {
		assertThat(new Position("a1")).isInstanceOf(Position.class);
	}

	@DisplayName("Position 생성자 Null 입력 예외 테스트")
	@ParameterizedTest
	@NullSource
	void create_null_exception(String nullInput) {
		assertThatThrownBy(() -> new Position(nullInput))
				.isInstanceOf(NullPointerException.class)
				.hasMessage("옳지 않은 좌표 입력입니다.");
	}

	@DisplayName("Position 생성자 빈 문자열 입력 예외 테스트")
	@ParameterizedTest
	@EmptySource
	void create_empty_exception(String emptyInput) {
		assertThatThrownBy(() -> new Position(emptyInput))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("옳지 않은 좌표 입력입니다.");
	}

	@DisplayName("Position 생성자 형식에서 벗어나는 입력 예외 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"aaa", "123", "a9", "z1"})
	void create_invalid_exception(String invalidInput) {
		assertThatThrownBy(() -> new Position(invalidInput))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("옳지 않은 좌표 입력입니다.");
	}
}