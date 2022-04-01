package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.direction.Direction;

class DirectionTest {

	@ParameterizedTest(name = "{0}에서 {1}은 {2}방향이다")
	@CsvSource(value = {
		"a1,a2,UP",
		"a2,a1,DOWN",
		"b1,a1,LEFT",
		"a1,b1,RIGHT",
		"h1,g2,UP_LEFT",
		"g1,h2,UP_RIGHT",
		"g2,f1,DOWN_LEFT",
		"g2,h1,DOWN_RIGHT",
		"b1,a3,UP_UP_LEFT",
		"b1,c3,UP_UP_RIGHT",
		"b3,a1,DOWN_DOWN_LEFT",
		"b3,c1,DOWN_DOWN_RIGHT",
		"c3,a4,LEFT_LEFT_UP",
		"c3,a2,LEFT_LEFT_DOWN",
		"f1,h2,RIGHT_RIGHT_UP",
		"f2,h1,RIGHT_RIGHT_DOWN"
	})
	void of(String from, String to, Direction expected) {
		Direction actual = Direction.of(Coordinate.of(from), Coordinate.of(to));
		assertThat(actual).isEqualTo(expected);
	}
}
