package chess.domain.direction.strategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.direction.BasicDirection;
import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.Direction;
import chess.domain.position.Position;

class BlackPawnDirectionStrategyTest {

	private final DirectionStrategy strategy = new BlackPawnDirectionStrategy();

	@ParameterizedTest
	@CsvSource(value = {"6:4", "5:4"}, delimiter = ':')
	@DisplayName("블랙 폰은 남쪽으로 갈 수 있다.")
	void moveBlackPawnFirst(int row, int column) {
		Direction direction = strategy.find(
			new Position(7, 4),
			new Position(row, column));
		assertThat(direction).isEqualTo(BasicDirection.SOUTH);
	}

	@ParameterizedTest
	@CsvSource(value = {"4:3:SOUTH_WEST", "4:5:SOUTH_EAST"}, delimiter = ':')
	@DisplayName("블랙 폰은 남동 남서쪽으로 갈 수 있다.")
	void moveBlackPawnDiagonal (int row, int column, String result) {
		Direction direction = strategy.find(
			new Position(5, 4),
			new Position(row, column));
		assertThat(direction).isEqualTo(DiagonalDirection.valueOf(result));
	}

}