package chess.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ColumnTest {

	@DisplayName("잘못된 문자로 컬럼을 생성하면 Exception")
	@ParameterizedTest
	@ValueSource(chars = {'A', 'i'})
	void of(char arongColumn) {
		assertThatThrownBy(() -> {
			Column.of(arongColumn);
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("Column 비교하여 차이 값 계산")
	@Test
	void minus() {
		Column columnB = Column.B;
		Column columnA = Column.A;

		assertThat(columnB.minus(columnA)).isEqualTo(1);
	}

	@DisplayName("other와 비교하여 더 큰지 비교")
	@Test
	void isGreaterThan() {
		Column columnE = Column.E;
		Column columnD = Column.D;

		assertThat(columnE.isGreaterThan(columnD)).isTrue();
	}

	@DisplayName("value만큼 더 한 Column 계산")
	@Test
	void add() {
		Column columnA = Column.A;
		assertThat(columnA.add(3)).isEqualTo(Column.D);
	}
}