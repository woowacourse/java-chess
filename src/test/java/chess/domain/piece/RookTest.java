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

class RookTest {
	static Stream<Arguments> generateMovablePositions() {
		return Stream.of(
				Arguments.of(Coordinates.of("C3"), Coordinates.of("C7")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("D3")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("A3")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("C1"))
		);
	}

	@Test
	public void constructor() {
		assertThat(new Rook(Color.WHITE)).isInstanceOf(Rook.class);
	}

	@ParameterizedTest
	@MethodSource("generateMovablePositions")
	void findMovablePositions(Coordinates from, Coordinates to) {
		List<Coordinates> coordinates = new Rook(Color.WHITE).findMovableCoordinates(from, to);
		assertThat(coordinates.contains(to)).isTrue();
	}

	@Test
	void findMovablePositions_NotMovableDirection_ExceptionThrown() {
		assertThatThrownBy(
				() -> new Rook(Color.WHITE).findMovableCoordinates(Coordinates.of("C3"), Coordinates.of("A4")))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("이동할 수 없는");
	}

	@Test
	void isKing() {
		assertThat(new Rook(Color.WHITE).isKing()).isFalse();
	}
}

