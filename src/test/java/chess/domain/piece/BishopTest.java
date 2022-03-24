package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.Position;

class BishopTest {

	private final Bishop bishop = Bishop.createBlack();

	@ParameterizedTest
	@CsvSource(value = {"5:5", "3:3", "7:7", "1:1", "2:2", "6:6", "8:8"}, delimiter = ':')
	@DisplayName("Bishop을 이동시킨다.")
	void moveBishop(int row, int column) {
		assertThat(bishop.isMovable(
			new Position(4, 4),
			new Position(row, column))
		).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"5:4", "2:3", "1:8", "5:6"}, delimiter = ':')
	@DisplayName("Bishop은 기울기 1인 지점이 아니면 갈 수 없다.")
	void moveBishopException(int row, int column) {
		assertThat(bishop.isMovable(
			new Position(4, 4),
			new Position(row, column))
		).isFalse();
	}
}