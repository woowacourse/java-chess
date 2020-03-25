package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DistanceTest {

	@ParameterizedTest
	@CsvSource({"c3, c4, 1", "c3, e5, 2", "c3, f3, 3", "c3, b4, 1", "c3, c1, 2", "c3, a1, 2", "c3, b3, 1", "c3, e1, 2"})
	void create_When_BothPositionInStraight(String start, String end, int expected) {
		Distance distance = Distance.of(Position.of(start), Position.of(end));
		assertThat(distance.getDistance()).isEqualTo(expected);
	}

	@Test
	void create_When_NotInStraight_Has_Zero() {
		Distance distance = Distance.of(Position.of("c3"), Position.of("d5"));
		assertThat(distance.getDistance()).isEqualTo(0);
	}
}
