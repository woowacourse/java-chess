package domain.piece.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DirectionTest {
	@DisplayName("모든 방향 리스트 반환")
	@ParameterizedTest
	@CsvSource({"N", "S", "E", "W", "NE", "NW", "SE", "SW"})
	void isEveryDirection_EveryDirection_ReturnTrue(Direction direction) {
		assertThat(Direction.isEveryDirection(direction)).isTrue();
	}

	@DisplayName("직선 방향 리스트 반환")
	@ParameterizedTest
	@CsvSource({"N", "S", "E", "W"})
	void isLinearDirection_LinearDirection_ReturnTrue(Direction direction) {
		assertThat(Direction.isLinearDirection(direction)).isTrue();
	}

	@DisplayName("대각선 방향 리스트 반환")
	@ParameterizedTest
	@CsvSource({"NE", "SE", "NW", "SW"})
	void isDiagonalDirection_DiagonalDirection_ReturnTrue(Direction direction) {
		assertThat(Direction.isDiagonalDirection(direction)).isTrue();
	}

	@DisplayName("Knight 방향 리스트 반환")
	@ParameterizedTest
	@CsvSource({"NNE", "NNW", "SSE", "SSW", "NEE", "NWW", "SEE", "SWW"})
	void isKnightDirection_KnightDirection_ReturnTrue(Direction direction) {
		assertThat(Direction.isKnightDirection(direction)).isTrue();
	}

	@DisplayName("White Pawn 방향 리스트 반환")
	@ParameterizedTest
	@CsvSource({"N", "NE", "NW"})
	void isWhitePawnDirection_WhitePawnDirection_ReturnTrue(Direction direction) {
		assertThat(Direction.isWhitePawnDirection(direction)).isTrue();
	}

	@DisplayName("Black Pawn 방향 리스트 반환")
	@ParameterizedTest
	@CsvSource({"S", "SE", "SW"})
	void isBlackPawnDirection_BlackPawnDirection_ReturnTrue(Direction direction) {
		assertThat(Direction.isBlackPawnDirection(direction)).isTrue();
	}
}
