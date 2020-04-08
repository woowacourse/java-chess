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

class WhitePawnTest {
	static Stream<Arguments> generateMovablePositions() {
		return Stream.of(
				Arguments.of(Coordinates.of("C3"), Coordinates.of("C4")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("B4")),
				Arguments.of(Coordinates.of("C3"), Coordinates.of("D4"))
		);
	}

	@Test
	public void constructor() {
		assertThat(new WhitePawn()).isInstanceOf(Pawn.class);
	}

	@ParameterizedTest
	@MethodSource("generateMovablePositions")
	void findMovablePositions(Coordinates from, Coordinates to) {
		List<Coordinates> coordinates = new WhitePawn().findMovableCoordinates(from, to);
		assertThat(coordinates.contains(to)).isTrue();
	}

	@Test
	void findMovablePositions_NotMovableDirection_ExceptionThrown() {
		assertThatThrownBy(
				() -> new WhitePawn().findMovableCoordinates(Coordinates.of("C3"), Coordinates.of("C2")))
				.isInstanceOf(PieceMoveFailedException.class)
				.hasMessageContaining("이동할 수 없는");
	}

	@Test
	void isKing() {
		assertThat(new WhitePawn().isKing()).isFalse();
	}
}