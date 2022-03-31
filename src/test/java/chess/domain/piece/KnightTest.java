package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.position.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.KnightDirection;

class KnightTest {

	private final Piece knight = Knight.createBlack();

	@ParameterizedTest
	@CsvSource(value = {"6:5", "5:6", "3:6", "2:5", "2:3", "3:2", "5:2", "6:3"}, delimiter = ':')
	@DisplayName("나이트가 움직일 수 있는 경우 움직인다.")
	void moveKnight(int row, int column) {
		Direction direction = knight.matchDirection(
			new Position(4, 4),
			new Position(row, column));
		assertThat(direction).isInstanceOf(KnightDirection.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"5:4", "6:4", "3:4", "2:4", "4:3", "4:5", "4:2", "4:5"}, delimiter = ':')
	@DisplayName("나이트가 움직일 수 없으면 예외가 발생한다.")
	void moveKnightException(int row, int column) {
		assertThatThrownBy(() -> knight.matchDirection(
			new Position(4, 4),
			new Position(row, column))
		).isInstanceOf(IllegalArgumentException.class);
	}
}