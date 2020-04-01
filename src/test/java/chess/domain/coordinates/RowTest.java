package chess.domain.coordinates;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class RowTest {
	@Test
	void ofTest() {
		assertThat(Row.of("1")).isEqualTo(Row.of("1"));
	}

	@ParameterizedTest
	@NullSource
	@ValueSource(strings = {"0", "9", "a"})
	void invalidValueTest(String input) {
		assertThatThrownBy(() -> Row.of(input))
				.isInstanceOf(IllegalArgumentException.class);
	}
}