package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.coordinates.Coordinates;

class KnightTest {
	static Stream<Arguments> generateMovablePositions() {
		return Stream.of(
				Arguments.of(Coordinates.of("C3"), Coordinates.of("D5")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("B5")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("B1")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("D1")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("E2"))
		);
	}

	@Test
	public void constructor() {
		assertThat(new Knight(Color.WHITE)).isInstanceOf(Knight.class);
	}

	@ParameterizedTest
	@MethodSource("generateMovablePositions")
	void findMovablePositions(Coordinates from, Coordinates to) {
		List<Coordinates> coordinates = new Knight(Color.WHITE).findMovableCoordinates(from, to);
		assertThat(coordinates.contains(to)).isTrue();
	}

	@Test
	void findMovablePositions_NotMovableDirection_ExceptionThrown() {
		assertThatThrownBy(
				() -> new Knight(Color.WHITE).findMovableCoordinates(Coordinates.of("C3"), Coordinates.of("C4")))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("이동할 수 없는");
	}

	@Test
	void isKing() {
		assertThat(new Knight(Color.WHITE).isKing()).isFalse();
	}
}