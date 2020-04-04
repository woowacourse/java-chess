package chess.domain.position;

import chess.domain.util.WrongPositionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionFactoryTest {

	@DisplayName("of 유효한 입력시 해당 Position 반환 테스트")
	@Test
	void of_when_valid_input_return_correct_position() {
		Position position1 = PositionFactory.of("a1");
		Position position2 = PositionFactory.of("a1");

		assertThat(position1).isEqualTo(position2);
	}

	@DisplayName("of 유효하지 않은 입력시 예외처리 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"aaa", "123", "a9", "z1"})
	void of_when_invalid_input_throw_exception(String input) {
		assertThatThrownBy(() -> PositionFactory.of(input))
				.isInstanceOf(WrongPositionException.class)
				.hasMessage("옳지 않은 좌표 입력입니다.");
	}
}