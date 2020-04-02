package chess.domain.coordinates;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class ColumnTest {
	@Test
	void ofTest() {
		assertThat(Column.of("a")).isEqualTo(Column.of("a"));
	}

	@ParameterizedTest
	@NullSource
	@ValueSource(strings = {"z", "1", "ㅎ"})
	void invalidNameTest(String input) {
		assertThatThrownBy(() -> Column.of(input))
				.isInstanceOf(IllegalArgumentException.class);
	}
}