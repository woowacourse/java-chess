package chess.domain.direction.strategy;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.direction.BasicDirection;
import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.Direction;
import chess.domain.position.Position;

class RoyalDirectionStrategyTest {

	private final DirectionStrategy strategy = new RoyalDirectionStrategy();

	@ParameterizedTest
	@CsvSource(value = {"5:5", "3:3", "7:7", "8:4", "4:7", "1:1"}, delimiter = ':')
	@DisplayName("King과 Queen이 갈 수 있는 방향을 확인한다.")
	void moveQueen(int row, int column) {
		Direction direction = strategy.find(
			new Position(4, 4),
			new Position(row, column));
		assertThat(direction).isInstanceOfAny(BasicDirection.class, DiagonalDirection.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"2:3", "1:8", "5:6"}, delimiter = ':')
	@DisplayName("King과 Queen의 규칙이 적용되지 않는 방향으로는 갈 수 없다.")
	void moveQueenInvalid(int row, int column) {
		assertThatThrownBy(() -> strategy.find(
			new Position(4, 4),
			new Position(row, column))
		).isInstanceOf(IllegalArgumentException.class);
	}

}