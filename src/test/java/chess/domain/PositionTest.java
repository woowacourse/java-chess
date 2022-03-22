package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Position 테스트")
class PositionTest {

	@DisplayName("좌표값을 입력해 위치를 지정할 떄 올바른 위치가 들어오면 인스턴스를 생성한다")
	@ParameterizedTest(name = "{index} {displayName} row={0} column={1}")
	@CsvSource(value = {"FIRST, a", "EIGHTH, h", "FIRST, h", "EIGHTH, a"})
	void valid_Position(final Row row, final Column column) {
		assertThatNoException().isThrownBy(() -> new Position(row, column));
	}
}