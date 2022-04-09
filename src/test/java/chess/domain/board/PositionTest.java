package chess.domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

	@ParameterizedTest
	@CsvSource(value = {"0, 0", "9, 9", "1, 0", "9, 1"})
	void overRangeInput(int row, int column) {
		assertThatThrownBy(() -> Position.of(row, column))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("체스판 범위를 벗어나는 입력입니다.");
	}
}
