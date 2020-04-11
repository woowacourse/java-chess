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
import chess.exception.PieceMoveFailedException;

class BishopTest {
	static Stream<Arguments> generateMovablePositions() {
		return Stream.of(
				Arguments.of(Coordinates.of("C3"), Coordinates.of("A1")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("E1")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("A5")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("E5")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("B2"))
		);
	}

	@Test
	public void constructor() {
		assertThat(new Bishop(Color.WHITE)).isInstanceOf(Bishop.class);
	}

	@ParameterizedTest
	@MethodSource("generateMovablePositions")
	void findMovablePositions(Coordinates from, Coordinates to) {
		List<Coordinates> coordinates = new Bishop(Color.WHITE).findMovableCoordinates(from, to);
		assertThat(coordinates.contains(to)).isTrue();
	}

	@Test
	void findMovablePositions_NotMovableDirection_ExceptionThrown() {
		assertThatThrownBy(
				() -> new Bishop(Color.WHITE).findMovableCoordinates(Coordinates.of("C3"), Coordinates.of("B3")))
				.isInstanceOf(PieceMoveFailedException.class)
				.hasMessageContaining("이동할 수 없는");
	}

	@Test
	void isKing() {
		assertThat(new Bishop(Color.WHITE).isKing()).isFalse();
	}
}