package chess.domain.direction.strategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.direction.BasicDirection;
import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.Direction;
import chess.domain.position.Position;

class WhitePawnDirectionStrategyTest {

	private final DirectionStrategy strategy = new WhitePawnDirectionStrategy();

	@ParameterizedTest
	@CsvSource(value = {"3:4", "4:4"}, delimiter = ':')
	@DisplayName("화이트 폰은 북쪽으로 이동 가능하다.")
	void moveWhitePawnFirst(int row, int column) {
		Direction direction = strategy.find(
			new Position(2, 4),
			new Position(row, column));
		assertThat(direction).isEqualTo(BasicDirection.NORTH);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:3:NORTH_WEST", "3:5:NORTH_EAST"}, delimiter = ':')
	@DisplayName("화이트 폰은 북동 북서 대각선으로 이동할 수 있다.")
	void moveWhitePawnDiagonal (int row, int column, String result) {
		assertThat(strategy.find(
			new Position(2, 4),
			new Position(row, column))).isEqualTo(DiagonalDirection.valueOf(result));
	}

}