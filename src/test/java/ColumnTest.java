import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.position.Column;

public class ColumnTest {
	@DisplayName("유효한 Column")
	@ParameterizedTest
	@ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7", "8"})
	void valid(String input) {
		assertThat(Column.contains(input)).isTrue();
	}

	@DisplayName("유효하지 않은 Column")
	@ParameterizedTest
	@ValueSource(strings = {"-1", "0", "9"})
	void invalid(String input) {
		assertThat(Column.contains(input)).isFalse();
	}
}
