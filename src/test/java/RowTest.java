import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.position.Row;

public class RowTest {
	@DisplayName("유효한 Row")
	@ParameterizedTest
	@ValueSource(strings = {"a", "b", "c", "d", "e", "f", "g", "h"})
	void valid(String input) {
		assertThat(Row.contains(input)).isTrue();
	}

	@DisplayName("유효하지 않은 Row")
	@ParameterizedTest
	@ValueSource(strings = {"l", "w", "y"})
	void invalid(String input) {
		assertThat(Row.contains(input)).isFalse();
	}

	@DisplayName("대소문자 구분 없이 유효한 Row")
	@ParameterizedTest
	@ValueSource(strings = {"a", "A", "c", "C", "e", "E", "g", "G"})
	void equalsIgnoreCaseValid(String input) {
		assertThat(Row.contains(input)).isTrue();
	}
}
