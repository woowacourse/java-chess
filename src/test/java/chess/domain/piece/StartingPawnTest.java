package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;

class StartingPawnTest {

	private static final String INITIAL_POSITION = "d2";

	static Stream<Arguments> move() {
		return Stream.of(
				Arguments.of(Position.from("c3"), Position.from("c3")),
				Arguments.of(Position.from("d3"), Position.from("d3")),
				Arguments.of(Position.from("d4"), Position.from("d4")),
				Arguments.of(Position.from("e3"), Position.from("e3"))
		);
	}

	@DisplayName("시작 위치의 폰이 이동한다.")
	@MethodSource
	@ParameterizedTest
	void move(Position target, Position expected) {
		Piece sut = new StartingPawn(Color.WHITE, Position.from(INITIAL_POSITION));
		Position actual = sut.move(target).position();
		assertThat(actual).isEqualTo(expected);
	}
}
