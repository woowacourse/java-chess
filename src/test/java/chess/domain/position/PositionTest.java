package chess.domain.position;

import static java.util.Arrays.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PositionTest {
	@DisplayName("행, 열을 입력받아 생성한 Position 객체가 올바르게 생성되는지 테스트")
	@Test
	void ofTest() {
		assertThat(Position.of("a1")).isEqualTo(Position.of("a1"));
	}

	@DisplayName("직선, 대각선 거리 이동시, 출발, 도착점을 제외한 position 리스트 반환")
	@ParameterizedTest
	@MethodSource("fromToListMoveSet")
	void findMultipleStepTraceTest(Position from, Position to, List<Position> expected) {
		List<Position> actual = Position.findMultipleStepTrace(from, to);
		assertThat(actual).isEqualTo(expected);
	}

	private static Stream<Arguments> fromToListMoveSet() {
		return Stream.of(
			Arguments.of(Position.of("a1"), Position.of("a4"), asList(Position.of("a2"), Position.of("a3"))),
			Arguments.of(Position.of("a4"), Position.of("a1"), asList(Position.of("a3"), Position.of("a2"))),
			Arguments.of(Position.of("a1"), Position.of("d1"), asList(Position.of("b1"), Position.of("c1"))),
			Arguments.of(Position.of("d1"), Position.of("a1"), asList(Position.of("c1"), Position.of("b1"))),
			Arguments.of(Position.of("a1"), Position.of("d4"), asList(Position.of("b2"), Position.of("c3"))),
			Arguments.of(Position.of("d4"), Position.of("a1"), asList(Position.of("c3"), Position.of("b2"))),
			Arguments.of(Position.of("a4"), Position.of("d1"), asList(Position.of("b3"), Position.of("c2"))),
			Arguments.of(Position.of("d1"), Position.of("a4"), asList(Position.of("c2"), Position.of("b3")))
		);
	}

	@DisplayName("직선, 대각선 외 이동시 IllegalArgumentException 발생")
	@ParameterizedTest
	@MethodSource("illegatFromToSet")
	void findMultipleStepTrace_illegal_route__exception_test(Position from, Position to) {
		assertThatThrownBy(() -> Position.findMultipleStepTrace(from, to))
			.isInstanceOf(IllegalArgumentException.class);
	}

	private static Stream<Arguments> illegatFromToSet() {
		return Stream.of(
			Arguments.of(Position.of("a1"), Position.of("c4")),
			Arguments.of(Position.of("c4"), Position.of("a1")),
			Arguments.of(Position.of("a1"), Position.of("d5")),
			Arguments.of(Position.of("d7"), Position.of("a1")),
			Arguments.of(Position.of("a1"), Position.of("g8")),
			Arguments.of(Position.of("d4"), Position.of("a8")),
			Arguments.of(Position.of("a4"), Position.of("d6")),
			Arguments.of(Position.of("d1"), Position.of("a7"))
		);
	}
}