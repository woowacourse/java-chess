package domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class PositionTest {

	@ParameterizedTest(name = "{index} - position: {0}, {1}")
	@CsvSource(value = {"0, 0", "9, 9", "1, 0", "9, 1"})
	void overRangeInput(int row, int column) {
		assertThatThrownBy(() -> Position.of(row, column))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("체스판 범위를 벗어나는 입력입니다.");
	}

	@Test
	void addDirection() {
		Position position = Position.of(4, 4);

		assertThat(position.addDirection(Direction.N)).isEqualTo(Position.of(5, 4));
	}

	@ParameterizedTest(name = "{index} - target: {0}, {1}")
	@CsvSource(value = {"4, 5", "5, 4", "4, 3", "3, 4"})
	void isLinerMove(int targetRow, int targetColumn) {
		Position source = Position.of(4, 4);

		assertThat(source.isLinerMove(Position.of(targetRow, targetColumn))).isTrue();
	}

	@ParameterizedTest(name = "{index} - target: {0}, {1}")
	@CsvSource(value = {"5, 5", "3, 3", "3, 5", "5, 3"})
	void isDiagonalMove(int targetRow, int targetColumn) {
		Position source = Position.of(4, 4);

		assertThat(source.isDiagonalMove(Position.of(targetRow, targetColumn))).isTrue();
	}


	@ParameterizedTest(name = "{index} - position: {0}, size: {1}")
	@MethodSource("createMovablePositionCount")
	void calculateMovableByDirection(Position startPosition, int size) {
		List<Direction> kingMoveDirection = Direction.getKingDirection();
		List<Position> movablePositions = startPosition.calculateMovableByDirection(kingMoveDirection);

		assertThat(movablePositions.size()).isEqualTo(size);
	}

	private static Stream<Arguments> createMovablePositionCount() {
		return Stream.of(
				Arguments.of(Position.of(4, 4), 8),
				Arguments.of(Position.of(1, 1), 3)
		);
	}

	@Test
	void calculateRowDifference() {
		Position source = Position.of(4, 4);
		Position target = Position.of(8, 8);

		assertThat(target.calculateRowDifference(source)).isEqualTo(1);
	}

	@Test
	void calculateColumnDifference() {
		Position source = Position.of(8, 8);
		Position target = Position.of(4, 4);

		assertThat(target.calculateRowDifference(source)).isEqualTo(-1);
	}

	@Test
	void subtractRow() {
		Position source = Position.of(4, 4);
		Position target = Position.of(8, 8);

		assertThat(target.subtractRow(source)).isEqualTo(4);
	}

	@Test
	void subtractColumn() {
		Position source = Position.of(8, 8);
		Position target = Position.of(4, 4);

		assertThat(target.subtractColumn(source)).isEqualTo(-4);
	}

	@Test
	void isEndColumn() {
		Position endColumn = Position.of(1, 8);
		Position center = Position.of(4, 4);

		assertAll(
				() -> assertThat(endColumn.isEndColumn()).isTrue(),
				() -> assertThat(center.isEndColumn()).isFalse()
		);
	}

	@ParameterizedTest(name = "{index} - position: {0}, {1} Team: {2} expectedValue: {3}")
	@CsvSource(value = {"7, 1, BLACK, true", "2, 1, WHITE, true", "4, 1, BLACK, false", "4, 1, WHITE, false"})
	void isDefaultRow(int row, int column, Team team, boolean expectedValue) {
		Position position = Position.of(row, column);

		assertThat(position.isInitialPawnRow(team)).isEqualTo(expectedValue);
	}
}
