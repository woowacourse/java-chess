package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Position 테스트")
class PositionTest {

	@Nested
	@DisplayName("좌표값을 입력해 위치를 지정할 떄")
	class initPositionTest {

		@DisplayName("잘못된 위치가 들어오면 에러를 발생시킨다.")
		@ParameterizedTest(name = "{index} {displayName} row={0} column={1}")
		@CsvSource(value = {"0, a", "9, h", "1, i"})
		void invalid_Position(final String row, final String column) {
			assertThatThrownBy(
				() -> new Position(row, column)
			).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("올바르지 않은 위치입니다");
		}

		@DisplayName("올바른 위치가 들어오면 인스턴스를 생성한다")
		@ParameterizedTest(name = "{index} {displayName} row={0} column={1}")
		@CsvSource(value = {"1, a", "8, h", "1, h", "8, a"})
		void valid_Position(final String row, final String column) {
			assertThatNoException().isThrownBy(() -> new Position(row, column));
		}
	}
}