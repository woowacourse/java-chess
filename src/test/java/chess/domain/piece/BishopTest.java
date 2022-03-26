package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.position.Position;
import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.Direction;

class BishopTest {

	private final Bishop bishop = Bishop.createBlack();

	@ParameterizedTest
	@CsvSource(value = {"5:5", "3:3", "7:7", "1:1", "2:2", "6:6", "8:8"}, delimiter = ':')
	@DisplayName("Bishop이 이동 가능한 범위의 방향을 검증한다.")
	void moveBishop(int row, int column) {
		Direction direction = bishop.matchDirection(
			new Position(4, 4),
			new Position(row, column));
		assertThat(direction).isInstanceOf(DiagonalDirection.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"5:4", "2:3", "1:8", "5:6"}, delimiter = ':')
	@DisplayName("Bishop은 기울기 1인 지점이 아니면 갈 수 없다.")
	void moveBishopException(int row, int column) {
		assertThatThrownBy(() -> bishop.matchDirection(
			new Position(4, 4), new Position(row, column)))
			.isInstanceOf(IllegalArgumentException.class);
	}
}