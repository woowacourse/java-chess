package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {

	@ParameterizedTest
	@CsvSource(value = {"5:5", "3:3", "7:7", "8:4", "4:7", "1:1"}, delimiter = ':')
	@DisplayName("Queen을 이동시킨다.")
	void moveQueen(int row, int column) {
		Queen queen = Queen.createBlack(4,4);
		queen.move(row, column);
		assertThat(queen.isSamePosition(row, column)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"2:3", "1:8", "5:6"}, delimiter = ':')
	@DisplayName("Queen은 Rook과 Bishop의 규칙이 적용되지 않는 곳은 이동할 수 없다.")
	void moveRookInvalid(int row, int column) {
		Queen queen = Queen.createBlack(4, 4);
		assertThatThrownBy(() -> queen.move(row, column))
			.isInstanceOf(IllegalArgumentException.class);
	}

}