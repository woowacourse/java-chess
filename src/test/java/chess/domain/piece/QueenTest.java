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

class QueenTest {
	static Stream<Arguments> generateMovablePositions() {
		return Stream.of(
				Arguments.of(Coordinates.of("C3"), Coordinates.of("C2")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("F3")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("F6")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("C7")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("B2"))
		);
	}

	@Test
	public void constructor() {
		assertThat(new Queen(Color.WHITE)).isInstanceOf(Queen.class);
	}

	@ParameterizedTest
	@MethodSource("generateMovablePositions")
	void findMovablePositions(Coordinates from, Coordinates to) {
		List<Coordinates> coordinates = new Queen(Color.WHITE).findMovableCoordinates(from, to);
		assertThat(coordinates.contains(to)).isTrue();
	}

	@Test
	void findMovablePositions_NotMovableDirection_ExceptionThrown() {
		assertThatThrownBy(
				() -> new Queen(Color.WHITE).findMovableCoordinates(Coordinates.of("C3"), Coordinates.of("A4")))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("이동할 수 없는");
	}

	@Test
	void isKing() {
		assertThat(new Queen(Color.WHITE).isKing()).isFalse();
	}
}