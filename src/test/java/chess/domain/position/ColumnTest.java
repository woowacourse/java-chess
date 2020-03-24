package chess.domain.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class ColumnTest {
	@Test
	void ofTest() {
		assertThat(Column.of("1")).isEqualTo(Column.ONE);
	}

	@ParameterizedTest
	@NullSource
	@ValueSource(strings = {"0", "9", "a"})
	void invalidValueTest(String input) {
		assertThatThrownBy(() -> Column.of(input))
			.isInstanceOf(IllegalArgumentException.class);
	}
}