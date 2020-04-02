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

class KingTest {
	static Stream<Arguments> generateMovablePositions() {
		return Stream.of(
				Arguments.of(Coordinates.of("C3"), Coordinates.of("C2")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("D3")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("B3")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("C4")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("B2"))
		);
	}

	@Test
	public void constructor() {
		assertThat(new King(Color.WHITE)).isInstanceOf(King.class);
	}

	@ParameterizedTest
	@MethodSource("generateMovablePositions")
	void findMovablePositions(Coordinates from, Coordinates to) {
		List<Coordinates> coordinates = new King(Color.WHITE).findMovableCoordinates(from, to);
		assertThat(coordinates.contains(to)).isTrue();
	}

	@Test
	void findMovablePositions_NotMovableDirection_ExceptionThrown() {
		assertThatThrownBy(
				() -> new King(Color.WHITE).findMovableCoordinates(Coordinates.of("C3"), Coordinates.of("A4")))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("이동할 수 없는");
	}

	@Test
	void isKing() {
		assertThat(new King(Color.WHITE).isKing()).isTrue();
	}
}