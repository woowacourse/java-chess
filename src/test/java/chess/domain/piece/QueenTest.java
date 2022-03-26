package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.position.Position;
import chess.domain.direction.BasicDirection;
import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.Direction;

class QueenTest {

	private Queen queen = Queen.createBlack();

	@ParameterizedTest
	@CsvSource(value = {"5:5", "3:3", "7:7", "8:4", "4:7", "1:1"}, delimiter = ':')
	@DisplayName("Queen을 이동시킨다.")
	void moveQueen(int row, int column) {
		Direction direction = queen.matchDirection(
			new Position(4, 4),
			new Position(row, column));
		assertThat(direction).isInstanceOfAny(BasicDirection.class, DiagonalDirection.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"2:3", "1:8", "5:6"}, delimiter = ':')
	@DisplayName("Queen은 Rook과 Bishop의 규칙이 적용되지 않는 곳은 이동할 수 없다.")
	void moveQueenInvalid(int row, int column) {
		assertThatThrownBy(() -> queen.matchDirection(
			new Position(4, 4),
			new Position(row, column))
		).isInstanceOf(IllegalArgumentException.class);
	}

}