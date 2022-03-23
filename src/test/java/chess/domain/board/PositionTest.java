package chess.domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

	@DisplayName("두 위치 간의 행 차이를 계산한다.")
	@Test
	void calculate_Row() {
		Position current = new Position(Row.FIRST, Column.a);
		Position target = new Position(Row.SECOND, Column.a);
		int actual = current.calculateRowDifference(target);

		assertThat(actual).isEqualTo(-1);
	}

	@DisplayName("두 위치 간의 열 차이를 계산한다.")
	@Test
	void calculate_Column() {
		Position current = new Position(Row.FIRST, Column.a);
		Position target = new Position(Row.FIRST, Column.b);
		int actual = current.calculateColumnDifference(target);

		assertThat(actual).isEqualTo(-1);
	}
}