package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RookTest {

	@ParameterizedTest
	@CsvSource(value = {"1:3", "8:3", "1:1"}, delimiter = ':')
	@DisplayName("Rook을 이동시킨다.")
	void moveRook(int row, int column) {
		Rook rook = Rook.createBlack(1, 3);
		rook.move(row, column);
		assertThat(rook.isSamePosition(row, column)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"2:4", "3:5"}, delimiter = ':')
	@DisplayName("Rook은 대각선으로 이동할 수 없다.")
	void moveRookInvalid(int row, int column) {
		Rook rook = Rook.createBlack(1, 3);
		assertThatThrownBy(() -> rook.move(row, column))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
