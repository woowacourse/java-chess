import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.position.Row;

public class RowTest {
	@DisplayName("유효한 Row")
	@ParameterizedTest
	@ValueSource(strings = {"a", "b", "c", "d", "e", "f", "g", "h"})
	void valid(String input) {
		assertDoesNotThrow(() -> Row.of(input));
	}

	@DisplayName("유효하지 않은 Row")
	@ParameterizedTest
	@ValueSource(strings = {"l", "w", "y"})
	void invalid(String input) {
		assertThatThrownBy(() -> Row.of(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("올바르지 않은 로우입니다.");
	}

	@DisplayName("대소문자 구분 없이 유효한 Row")
	@ParameterizedTest
	@ValueSource(strings = {"a", "A", "c", "C", "e", "E", "g", "G"})
	void equalsIgnoreCaseValid(String input) {
		assertDoesNotThrow(() -> Row.of(input));
	}
}
