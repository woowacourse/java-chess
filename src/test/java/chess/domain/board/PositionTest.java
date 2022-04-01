package chess.domain.board;

import static chess.domain.board.File.A;
import static chess.domain.board.File.D;
import static chess.domain.board.File.H;
import static chess.domain.board.Rank.EIGHT;
import static chess.domain.board.Rank.FIVE;
import static chess.domain.board.Rank.FOUR;
import static chess.domain.board.Rank.ONE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Team;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class PositionTest {

	@Test
	void addDirection() {
		Position position = Position.of(FOUR, D);

		assertThat(position.addDirection(Direction.N)).isEqualTo(Position.of(FIVE, D));
	}

	@ParameterizedTest
	@CsvSource(value = {"FOUR, E", "FIVE, D", "FOUR, C", "THREE, D"})
	void isLinerMove(Rank rank, File file) {
		Position source = Position.of(FOUR, D);

		assertThat(source.isLinerMove(Position.of(rank, file))).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"FIVE, E", "THREE, C", "THREE, E", "FIVE, C"})
	void isDiagonalMove(Rank rank, File file) {
		Position source = Position.of(FOUR, D);

		assertThat(source.isDiagonalMove(Position.of(rank, file))).isTrue();
	}


	@ParameterizedTest
	@MethodSource("createArrivalPositionCount")
	void getArrivalPositionsByDirection(Position startPosition, int size) {
		List<Direction> kingMoveDirection = Direction.getKingDirection();
		List<Position> movablePositions = startPosition.getArrivalPositionsByDirections(kingMoveDirection);

		assertThat(movablePositions.size()).isEqualTo(size);
	}

	private static Stream<Arguments> createArrivalPositionCount() {
		return Stream.of(
				Arguments.of(Position.of(FOUR, D), 8),
				Arguments.of(Position.of(ONE, A), 3)
		);
	}

	@Test
	void calculateRankDifference() {
		Position source = Position.of(FOUR, D);
		Position target = Position.of(EIGHT, H);

		assertThat(target.calculateRankDifference(source)).isEqualTo(1);
	}

	@Test
	void calculateFileDifference() {
		Position source = Position.of(EIGHT, H);
		Position target = Position.of(FOUR, D);

		assertThat(target.calculateFileDifference(source)).isEqualTo(-1);
	}

	@Test
	void subtractRank() {
		Position source = Position.of(FOUR, D);
		Position target = Position.of(EIGHT, H);

		assertThat(target.subtractRank(source)).isEqualTo(4);
	}

	@Test
	void subtractFile() {
		Position source = Position.of(EIGHT, H);
		Position target = Position.of(FOUR, D);

		assertThat(target.subtractFile(source)).isEqualTo(-4);
	}

	@Test
	void isEndFile() {
		Position endColumn = Position.of(ONE, H);
		Position center = Position.of(FOUR, D);

		assertAll(
				() -> assertThat(endColumn.isEndFile()).isTrue(),
				() -> assertThat(center.isEndFile()).isFalse()
		);
	}

	@ParameterizedTest
	@CsvSource(value = {"SEVEN, A, BLACK, true", "TWO, A, WHITE, true", "FOUR, A, BLACK, false",
			"FOUR, A, WHITE, false"})
	void isInitialPawnRank(Rank rank, File file, Team team, boolean expectedValue) {
		Position position = Position.of(rank, file);

		assertThat(position.isInitialPawnRank(team)).isEqualTo(expectedValue);
	}
}
