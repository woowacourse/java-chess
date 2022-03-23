package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {

	@ParameterizedTest
	@CsvSource(value = {"5:5", "3:3", "7:7"}, delimiter = ':')
	@DisplayName("Bishop을 이동시킨다.")
	void moveBishop(int row, int column) {
		Bishop bishop = Bishop.createBlack(4, 4);
		bishop.move(row, column);
		assertThat(bishop.isSamePosition(row, column)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"5:4", "2:3", "1:8", "5:6"}, delimiter = ':')
	@DisplayName("Bishop은 기울기 1인 지점이 아니면 갈 수 없다.")
	void moveBishopException(int row, int column) {
		Bishop bishop = Bishop.createBlack(4, 4);
		assertThatThrownBy(() -> bishop.move(row, column))
			.isInstanceOf(IllegalArgumentException.class);
	}
}